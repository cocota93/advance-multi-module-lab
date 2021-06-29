package org.jedy.productOrder.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jedy.address.Address;
import org.jedy.payment.domain.Payment;
import org.jedy.payment.domain.PaymentType;
import org.jedy.product.domain.Product;
import org.jedy.productOrder.domain.ProductOrderDeliveryStatus;
import org.jedy.productOrder.domain.ProductOrderStatus;
import org.jedy.productOrderUnit.domain.ProductOrderUnit;
import org.jedy.productOrderUnit.domain.ProductOrderUnitStatus;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProductOrderDetailResponse {
    private Long id;
    private ProductOrderStatus orderStatus;
    private List<ProductOrderUnitResponse> productOrderUnits;
    private Long totalPrice;
    private ProductOrderDeliveryStatus deliveryStatus; //READY, COMP
    private Address address;
    private PaymentResponse payment;

    @QueryProjection
    @Builder
    public ProductOrderDetailResponse(Long id, ProductOrderStatus orderStatus, List<ProductOrderUnit> productOrderUnits, Long totalPrice, ProductOrderDeliveryStatus deliveryStatus, Address address, Payment payment) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.productOrderUnits = productOrderUnits.stream()
                                                  .map(unit -> new ProductOrderUnitResponse(
                                                          unit.getId(),
                                                          unit.getProductOrderUnitStatus(),
                                                          unit.getProduct(),
                                                          unit.getPrice(),
                                                          unit.getCount()
                                                  ))
                                                  .collect(Collectors.toList());
        this.totalPrice = totalPrice;
        this.deliveryStatus = deliveryStatus;
        this.address = address;
        this.payment = PaymentResponse.builder()
                                      .id(payment.getId())
                                      .paymentType(payment.getPaymentType())
                                      .price(payment.getPrice())
                                      .build();
    }

    @NoArgsConstructor
    @Data
    public static class ProductOrderUnitResponse {
        private Long id;
        private ProductOrderUnitStatus productOrderUnitStatus;
        private ProductResponse productResponse;
        private Long price;
        private Integer count;

        public ProductOrderUnitResponse(Long id, ProductOrderUnitStatus productOrderUnitStatus, Product product, Long price, Integer count) {
            this.id = id;
            this.productOrderUnitStatus = productOrderUnitStatus;
            this.productResponse = ProductResponse.builder()
                                                  .id(product.getId())
                                                  .name(product.getName())
                                                  .upperCatCd(product.getUpperCatCd())
                                                  .build();
            this.price = price;
            this.count = count;
        }
    }


    @Data
    public static class ProductResponse {
        private Long id;
        private String upperCatCd;
        private String name;

        @Builder
        public ProductResponse(Long id, String upperCatCd, String name) {
            this.id = id;
            this.upperCatCd = upperCatCd;
            this.name = name;
        }
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
