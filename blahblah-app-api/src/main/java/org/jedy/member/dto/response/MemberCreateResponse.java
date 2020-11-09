package org.jedy.member.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jedy.member_core.domain.Member;
import org.jedy.member_core.domain.MemberAuth;
import org.jedy.member_core.domain.MemberAuthType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCreateResponse {

    private Long id;

    private String loginId;

    private String name;

    private String email;

    private List<MemberAuthType> authList = new ArrayList<>();

    public MemberCreateResponse(Member member) {
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.authList = member.getAuthorityList().stream()
                .map(MemberAuth::getType)
                .collect(Collectors.toList());
    }
}
