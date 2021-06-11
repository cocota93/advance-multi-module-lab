package org.jedy.member.dto;

import lombok.Data;

@Data
public class MemberSearchCondition {

    private String name;
    private Integer ageGoe;
    private Integer ageLoe;
}
