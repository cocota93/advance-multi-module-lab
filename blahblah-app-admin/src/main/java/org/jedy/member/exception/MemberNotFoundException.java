package org.jedy.member.exception;

import org.jedy.system_core.global.error.exception.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {

    public MemberNotFoundException(String target) {
        super(target + "is not found");
    }
}
