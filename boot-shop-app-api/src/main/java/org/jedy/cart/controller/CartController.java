package org.jedy.cart.controller;

import lombok.RequiredArgsConstructor;
import org.jedy.cart.domain.Cart;
import org.jedy.cart.dto.request.CartAddRequest;
import org.jedy.cart.service.CartService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping(value = "/search")
    public Page<Cart> search(@AuthenticationPrincipal UserDetails userDetails) {
        Page<Cart> search = cartService.search(userDetails.getUsername());
        return search;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Void> add(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CartAddRequest cartAddRequest) {
        cartService.add(userDetails.getUsername(), cartAddRequest);
        return ResponseEntity.ok().build();
    }

}
