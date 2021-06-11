package org.jedy.member.domain;

import lombok.Data;

@Data
public class ReqSignupMember {

    private String loginId;
    private String password;
    private String name;
    private String email;
    private Integer age;

}
