package org.jedy.oembed_core.util;

import org.jedy.oembed_core.domain.OembedProviderType;
import org.jedy.system_core.global.error.exception.BusinessException;
import org.jedy.system_core.global.error.exception.ErrorCode;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* api호출시 각 제공자가 정해둔 특정 파라미터를 더 줄수 있는데
* 모든 파라미터에 대해 대응할수있도록 다 오버라이딩 해봤자 많이 안쓸것같고
* 실제로 어떤걸 쓰게될지 알수없기때문에 기본적인 호출만 만들어두고
* 그외에는 구체적인 기획에 따라 대응
*
* */

public class OembedUrlUtil {

    //fixedhost가 필요한 이유가 잘 드러나지 않는것 같다.
    //컨텐츠 제공자별로 여러가지 host형태를 제공해주는게 문제다.
    //예를들면 유튜브는 아래의 형태를 모두 제공해줘야한다.
    // ex)https://www.youtube.com/watch?v=Chgq1SWxa5Q&feature=youtu.be , https://youtu.be/Chgq1SWxa5Q
    // 컨텐츠 제공자별로 제공해주는 형태를 모두 어떤 리스트에 담아두고 관리하는게 나을까?
    // 근데 시간이 지나도 여기서 더 건드릴만한 일이 없을것같은데 너무 오버하는거 아닌가?
    public static URL createApiUrl(String url) throws MalformedURLException {
        String host = "";
        if(!"http".equals(url.substring(0, 4))){
            host = new URL("http://" + url).getHost();
        }else{
            host = new URL(url).getHost();
        }
        String fixedHost = host.replace(".", "").toUpperCase();
        OembedProviderType providerType = Arrays.stream(OembedProviderType.values())
                .filter(src -> fixedHost.contains(src.toString()))
                .findAny().orElseThrow(() -> new BusinessException(ErrorCode.NOT_SUPPORT_PROVIDER_TYPE))
                ;

        StringBuilder sb = new StringBuilder();
        switch (providerType){
            case YOUTUBE:{
                sb.append("https://www.youtube.com/oembed?url=");
                sb.append(url);
                sb.append("&format=json");
                break;
            }
            case INSTAGRAM:{
                throw new BusinessException(ErrorCode.NOT_SUPPORT_PROVIDER_TYPE);
                //break;
            }
            case TWITTER:{
                sb.append("https://publish.twitter.com/oembed?url=");
                sb.append(url);
                break;
            }
            case VIMEO:{
                sb.append("https://vimeo.com/api/oembed.json?url=");
                sb.append(url);
                break;
            }
            default:{
                throw new BusinessException(ErrorCode.NOT_SUPPORT_PROVIDER_TYPE);
            }
        }

        return new URL(sb.toString());
    }
}
