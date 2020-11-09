package org.jedy.member.exception;

import org.jedy.system_core.global.error.exception.EntityNotFoundException;

public class MemberLoginFailException extends EntityNotFoundException {

    public MemberLoginFailException(String target) {
        super(target + "login fail");
    }
}
