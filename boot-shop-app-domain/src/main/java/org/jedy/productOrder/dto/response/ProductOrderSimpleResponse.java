package org.jedy.productOrder.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import org.jedy.address.Address;
import org.jedy.productOrder.domain.ProductOrderDeliveryStatus;
import org.jedy.productOrder.domain.ProductOrderStatus;

@Data
public class ProductOrderSimpleResponse {
    private Long id;
    private ProductOrderStatus orderStatus;
    private Long totalPrice;
    private ProductOrderDeliveryStatus deliveryStatus; //READY, COMP
    private Address address;

    @QueryProjection
    public ProductOrderSimpleResponse(Long id, ProductOrderStatus orderStatus, Long totalPrice, ProductOrderDeliveryStatus deliveryStatus, Address address) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.deliveryStatus = deliveryStatus;
        this.address = address;
    }
}
