package org.jedy.cart.dto.request;

import lombok.Data;

@Data
public class CartModifyRequest {
    private Long cartId;
    private Long productId;
    private int count;
}
