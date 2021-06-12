package org.jedy.operator.exception;

import org.jedy.system_core.global.error.exception.BusinessException;
import org.jedy.system_core.global.error.exception.ErrorCode;

public class OperatorLoginAuthorityAssignInvalidException extends BusinessException {

    public OperatorLoginAuthorityAssignInvalidException(String operatorId) {
        super(operatorId + " 는 제대로된 권한 부여가 되어있지 않습니다.", ErrorCode.INVALID_AUTHORITY_ASSIGN);
    }
}
