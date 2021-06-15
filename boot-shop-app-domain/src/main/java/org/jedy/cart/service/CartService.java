package org.jedy.cart.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.cart.domain.Cart;
import org.jedy.cart.dto.request.CartAddRequest;
import org.jedy.cart.dto.response.CartResponse;
import org.jedy.cart.repository.CartRepository;
import org.jedy.product.domain.Product;
import org.jedy.product.repository.ProductRepository;
import org.jedy.system_core.global.error.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Page<Cart> search(String memberLoginId) {
        List<CartResponse> useCartList = cartRepository.findUseCartList(memberLoginId, true);
        PageImpl page = new PageImpl(useCartList);
        return page;
    }

    public void add(String loginId, CartAddRequest cartAddRequest) {
        Cart emptyCart = cartRepository.findEmptyCart(loginId, false);
        Product product = productRepository.findById(cartAddRequest.getProductId()).orElseThrow(() -> new EntityNotFoundException(cartAddRequest.getProductId() + " "));
        emptyCart.changeProduct(product, cartAddRequest.getCount());
    }
}

