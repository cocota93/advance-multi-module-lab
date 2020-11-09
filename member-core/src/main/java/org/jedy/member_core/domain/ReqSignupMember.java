package org.jedy.member_core.domain;

import lombok.Data;

@Data
public class ReqSignupMember {

    private String loginId;
    private String password;
    private String name;
    private String email;

}
