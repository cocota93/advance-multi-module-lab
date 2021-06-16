package org.jedy.cart.controller;

import lombok.RequiredArgsConstructor;
import org.jedy.cart.domain.Cart;
import org.jedy.cart.dto.request.CartAddRequest;
import org.jedy.cart.dto.request.CartDeleteRequest;
import org.jedy.cart.dto.request.CartModifyRequest;
import org.jedy.cart.dto.response.CartResponse;
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
    public Page<CartResponse> search(@AuthenticationPrincipal UserDetails userDetails) {
        Page<CartResponse> search = cartService.search(userDetails.getUsername());
        return search;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Void> add(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CartAddRequest cartAddRequest) {
        cartService.add(userDetails.getUsername(), cartAddRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/modify")
    public ResponseEntity<Void> update(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CartModifyRequest cartModifyRequest) {
        cartService.modify(userDetails.getUsername(), cartModifyRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CartDeleteRequest cartDeleteRequest) {
        cartService.delete(userDetails.getUsername(), cartDeleteRequest);
        return ResponseEntity.ok().build();
    }
}
