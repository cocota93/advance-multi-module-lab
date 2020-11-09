package org.jedy.operator.exception;

import org.jedy.system_core.global.error.exception.BusinessException;
import org.jedy.system_core.global.error.exception.ErrorCode;

public class OperatorAuthAddException extends BusinessException {

    public OperatorAuthAddException(ErrorCode errorCode) {
        super(errorCode);
    }
}
