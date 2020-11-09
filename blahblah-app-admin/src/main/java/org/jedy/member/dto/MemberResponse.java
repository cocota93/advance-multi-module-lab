package org.jedy.member.dto;

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
}
