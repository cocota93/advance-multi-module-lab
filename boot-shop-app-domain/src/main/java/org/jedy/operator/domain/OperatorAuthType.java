package org.jedy.operator.domain;

import org.springframework.security.core.GrantedAuthority;

public enum OperatorAuthType implements GrantedAuthority {
    SUPER_MANAGER("최고관리자"),
    SUB_MANAGER("서브관리자"),
    PAY_MANAGER("결제관리자");

    private String description;

    OperatorAuthType(String description) {
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return this.name();
    }
}
