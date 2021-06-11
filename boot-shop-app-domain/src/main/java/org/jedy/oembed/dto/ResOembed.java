package org.jedy.oembed.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.jedy.oembed.domain.OembedProviderType;
import org.jedy.system_core.entity.BaseTimeEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/*
* 설계의도 : oEmbed컨텐츠를 제공하는 제공자(ex. youtube, insta, vimeo, twitter, etc)
* 는 여러군데지만 제공되는 데이터를 단순 수집하는데 목적이 있기 때문에
* 하나의 테이블로 관리하는게 맞는것 같다.
* 또한 아래와같이 랩퍼클래스를 따로 만들어 컨텐츠 제공자별 로직을 분리를
* 고려해봤으나 그런게 필요할 정도로 로직차이가 클거라 생각되지 않기 때문에 만들지는 않았습니다.
* Oembed oembed = ConverterUtil.convertJsonStringToObject(response.toString(), Oembed.class);
* YoutubeOembed youtubeOembed = new YoutubeOembed(oembed);
* VimeoOembed vimeoOembed = new VimeoOembed(oembed);
* */

//@Data
@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResOembed extends BaseTimeEntity {
    private Long id;
    private OembedProviderType providerType;

    private String title;
    private String author_name;
    private String author_url;
    private String type;
    private Integer height;
    private Integer width;
    private String version;
    private String provider_name;
    private String provider_url;
    private Integer thumbnail_height;
    private Integer thumbnail_width;
    private String thumbnail_url;
    private String html;

    private String cache_age;//트위터

    //vimeo
    private String is_plus;
    private String account_type;
    private Integer duration;
    private String description;
    private String thumbnail_url_with_play_button;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime upload_date;
    private Integer video_id;
    private String uri;


    private Boolean deleted = false;

    public void delete(){
        deleted = true;
    }

}
