package org.jedy.cart.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.cart.domain.Cart;
import org.jedy.cart.dto.request.CartAddRequest;
import org.jedy.cart.dto.request.CartDeleteRequest;
import org.jedy.cart.dto.request.CartModifyRequest;
import org.jedy.cart.dto.response.CartResponse;
import org.jedy.cart.repository.CartRepository;
import org.jedy.product.domain.Product;
import org.jedy.product.repository.ProductRepository;
import org.jedy.system_core.global.error.exception.BusinessException;
import org.jedy.system_core.global.error.exception.EntityNotFoundException;
import org.jedy.system_core.global.error.exception.ErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Page<CartResponse> search(String memberLoginId) {
        List<Cart> useCartList = cartRepository.findUseCartList(memberLoginId, true);
        PageImpl page = new PageImpl(
                useCartList.stream().map(cart ->
                        new CartResponse(cart.getId(), cart.getProduct(), cart.getCount())
                ).collect(Collectors.toList())
        );
        return page;
    }

    public void add(String loginId, CartAddRequest cartAddRequest) {
        Cart emptyCart = cartRepository.findEmptyCart(loginId, false);
        Product product = productRepository.findById(cartAddRequest.getProductId()).orElseThrow(() -> new EntityNotFoundException(cartAddRequest.getProductId() + " "));
        emptyCart.changeProduct(product, cartAddRequest.getCount());
    }

    public void modify(String username, CartModifyRequest cartModifyRequest) {
        Cart targetCart = cartRepository.findById(cartModifyRequest.getCartId())
                                        .orElseThrow(() -> new EntityNotFoundException(cartModifyRequest.getCartId() + " "));
        if (!targetCart.equalOwner(username))
            throw new BusinessException("수정을 요청한 유저(" + username + ")와 장바구니의 실제 주인이 다릅니다.", ErrorCode.INVALID_INPUT_VALUE);

        Product product = productRepository.findById(cartModifyRequest.getProductId())
                                           .orElseThrow(() -> new EntityNotFoundException(cartModifyRequest.getProductId() + " "));
        targetCart.changeProduct(product, cartModifyRequest.getCount());
    }

    public void delete(String username, CartDeleteRequest cartDeleteRequest) {
        Cart targetCart = cartRepository.findById(cartDeleteRequest.getCartId())
                                        .orElseThrow(() -> new EntityNotFoundException(cartDeleteRequest.getCartId() + " "));
        if (!targetCart.equalOwner(username))
            throw new BusinessException("수정을 요청한 유저(" + username + ")와 장바구니의 실제 주인이 다릅니다.", ErrorCode.INVALID_INPUT_VALUE);

        targetCart.disableSlot();
    }
}

