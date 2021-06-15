package org.jedy.cart.repository;

import org.jedy.cart.domain.Cart;
import org.jedy.cart.dto.response.CartResponse;

import java.util.List;

public interface CartRepositoryCustom {

    List<CartResponse> findUseCartList(String memberLoginId, Boolean use);
    Cart findEmptyCart(String memberLoginId, Boolean use);
}
