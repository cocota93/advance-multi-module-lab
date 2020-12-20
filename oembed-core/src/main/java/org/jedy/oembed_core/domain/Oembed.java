package org.jedy.oembed_core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jedy.system_core.entity.BaseEntity;
import org.jedy.system_core.entity.BaseTimeEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Oembed extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oembed_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private OembedProviderType providerType;

    private String title;
    @Column(name = "author_name")
    private String author_name;
    @Column(name = "author_url")
    private String author_url;
    private String type;
    private Integer height;
    private Integer width;
    private String version;
    @Column(name = "provider_name")
    private String provider_name;
    @Column(name = "provider_url")
    private String provider_url;
    @Column(name = "thumbnail_height")
    private Integer thumbnail_height;
    @Column(name = "thumbnail_width")
    private Integer thumbnail_width;
    @Column(name = "thumbnail_url")
    private String thumbnail_url;
    @Lob
    private String html;

    @Column(name = "cache_age")
    private String cache_age;//트위터

    //vimeo
    @Column(name = "is_plus")
    private String is_plus;
    @Column(name = "account_type")
    private String account_type;
    private Integer duration;
    private String description;
    @Column(name = "thumbnail_url_with_play_button")
    private String thumbnail_url_with_play_button;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime upload_date;
    @Column(name = "video_id")
    private Integer video_id;
    private String uri;


    @Column(nullable = false)
    private Boolean deleted = false;

    public void delete(){
        deleted = true;
    }

}
