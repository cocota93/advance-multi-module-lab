package org.jedy.member_core.domain;

import org.jedy.system_core.support.EnumType;
import org.springframework.security.core.GrantedAuthority;

public enum MemberAuthType implements GrantedAuthority, EnumType {
    BOARD_MANAGER("게시물 관리자"),
    COMMON_USER("일반유저");

    private String description;

    MemberAuthType(String description) {
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return this.name();
    }


    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getMessage() {
        return this.description;
    }
}
