package org.jedy.operator.exception;

import org.jedy.system_core.global.error.exception.EntityNotFoundException;

public class OperatorLoginIdNotFoundException extends EntityNotFoundException {


    public OperatorLoginIdNotFoundException(String operatorLoginId) {
        super(operatorLoginId + " 는 존재하지 않는 ID 입니다.");
    }
}
