package org.jedy.member.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jedy.member_core.domain.Member;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {

    private String loginId;
    private String name;
    private String email;
    private Integer age;

    public MemberResponse(final Member member) {
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.age = member.getAge();
    }

    @QueryProjection
    public MemberResponse(final String loginId, final String name, final String email, final Integer age) {
        this.loginId = loginId;
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
