package org.jedy.cart.repository;

import org.jedy.cart.domain.Cart;

import java.util.List;

public interface CartRepositoryCustom {

    List<Cart> findUseCartList(String memberLoginId, Boolean useSlot);
    Cart findEmptyCart(String memberLoginId, Boolean useSlot);
}
