package org.jedy.cart.exception;

import org.jedy.system_core.global.error.exception.EntityNotFoundException;

public class EmptyCartNotFindException extends EntityNotFoundException {

    public EmptyCartNotFindException(String memberLoginId) {
        super(memberLoginId + " empty cart not found");
    }
}
