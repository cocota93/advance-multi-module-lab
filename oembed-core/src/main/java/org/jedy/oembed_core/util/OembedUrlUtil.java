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
* url분리 정규표현식 출처 : https://lottogame.tistory.com/4647
* */

public class OembedUrlUtil {

    public static URL createApiUrl(String url) throws MalformedURLException {
        final String regex = "(^(http[s]?|ftp):\\/)?\\/?([^:\\/\\s]+)((\\/\\w+)*\\/)([\\w\\-\\.]+[^#?\\s]+)(.*)?(#[\\w\\-]+)?$";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(url);
        String host = "";
        if(matcher.find()){
            host = matcher.group(3);
        }

        String fixedHost = host.replace(".", "").toUpperCase();
        OembedProviderType providerType = Arrays.stream(OembedProviderType.values()).filter(src -> fixedHost.contains(src.toString())).findAny().orElseThrow(() -> new BusinessException(ErrorCode.NOT_SUPPORT_PROVIDER_TYPE));

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
