package org.jedy.member.exception;

import org.jedy.system_core.global.error.exception.BusinessException;
import org.jedy.system_core.global.error.exception.ErrorCode;

public class MemberSignupDuplicationException extends BusinessException {
    public MemberSignupDuplicationException(String loginId) {
        super("해당 아이디 ( " + loginId + " ) 이미 존재합니다. " , ErrorCode.LOGIN_ID_DUPLICATION);
    }


}
