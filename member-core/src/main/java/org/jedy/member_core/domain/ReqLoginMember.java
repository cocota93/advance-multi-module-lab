package org.jedy.member_core.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqLoginMember {

    @NotBlank
    String loginId;

    @NotBlank
    String password;

}
