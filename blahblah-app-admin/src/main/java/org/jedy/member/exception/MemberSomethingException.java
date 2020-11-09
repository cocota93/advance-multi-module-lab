package org.jedy.member.exception;


import org.jedy.system_core.global.error.exception.BusinessException;
import org.jedy.system_core.global.error.exception.ErrorCode;

public class MemberSomethingException extends BusinessException {

    public MemberSomethingException() {
        super(ErrorCode.SOMETHING_INVALID);
    }
}
