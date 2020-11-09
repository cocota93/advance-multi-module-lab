package org.jedy.member.exception;

import org.jedy.system_core.global.error.exception.EntityNotFoundException;

public class MemberNotFindException extends EntityNotFoundException {

    public MemberNotFindException(String target) {
        super(target + "find fail");
    }
}
