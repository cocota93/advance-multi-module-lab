package org.jedy.cart.dto.request;

import lombok.Data;

@Data
public class CartAddRequest {
    private Long productId;
    private int count;
}
