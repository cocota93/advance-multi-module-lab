package org.jedy.member.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jedy.member_core.domain.Member;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {

    private String name;

    public MemberResponse(final Member member) {
        this.name = member.getName();
    }

    @QueryProjection
    public MemberResponse(final String name) {
        this.name = name;
    }
}
