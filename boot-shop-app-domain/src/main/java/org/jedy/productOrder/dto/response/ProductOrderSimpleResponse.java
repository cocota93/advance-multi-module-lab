package org.jedy.productOrder.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import org.jedy.address.Address;
import org.jedy.payment.domain.Payment;
import org.jedy.payment.domain.PaymentType;
import org.jedy.productOrder.domain.ProductOrderDeliveryStatus;
import org.jedy.productOrder.domain.ProductOrderStatus;

@Data
public class ProductOrderSimpleResponse {
    private Long id;
    private ProductOrderStatus orderStatus;
    private Long totalPrice;
    private ProductOrderDeliveryStatus deliveryStatus; //READY, COMP
    private Address address;
    private PaymentResponse payment;

    @QueryProjection
    public ProductOrderSimpleResponse(Long id, ProductOrderStatus orderStatus, Long totalPrice, ProductOrderDeliveryStatus deliveryStatus, Address address, Payment payment) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.deliveryStatus = deliveryStatus;
        this.address = address;
        this.payment = PaymentResponse.builder()
                                      .id(payment.getId())
                                      .paymentType(payment.getPaymentType())
                                      .price(payment.getPrice())
                                      .build();
    }

    @Data
    static class PaymentResponse {
        private Long id;
        private PaymentType paymentType;
        private Long price;

        @Builder
        public PaymentResponse(Long id, PaymentType paymentType, Long price) {
            this.id = id;
            this.paymentType = paymentType;
            this.price = price;
        }
    }
}
