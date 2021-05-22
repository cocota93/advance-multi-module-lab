package org.jedy.oembed_core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.jedy.system_core.entity.BaseTimeEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OembedReceiver {
//    private Long id;
//    private OembedProviderType providerType;

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
}
