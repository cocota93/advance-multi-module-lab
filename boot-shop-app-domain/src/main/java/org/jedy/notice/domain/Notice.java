package org.jedy.notice.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jedy.system_core.entity.BaseEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    private String content;

    @Column(nullable = false)
    private Boolean deleted = false;

    public Notice(String content) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void delete(){
        deleted = true;
    }

}
