package org.jedy.productOrder.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jedy.address.Address;
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

    @QueryProjection
    @Builder
    public ProductOrderDetailResponse(Long id, ProductOrderStatus orderStatus, List<ProductOrderUnit> productOrderUnits, Long totalPrice, ProductOrderDeliveryStatus deliveryStatus, Address address) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.productOrderUnits = productOrderUnits.stream()
                                                  .map(unit -> new ProductOrderUnitResponse(
                                                          unit.getId(),
                                                          unit.getProductOrderUnitStatus(),
                                                          unit.getPrice(),
                                                          unit.getCount()
                                                  ))
                                                  .collect(Collectors.toList());
        this.totalPrice = totalPrice;
        this.deliveryStatus = deliveryStatus;
        this.address = address;
    }

    @NoArgsConstructor
    @Data
    public static class ProductOrderUnitResponse {
        private Long id;
        private ProductOrderUnitStatus productOrderUnitStatus;
        private Long price;
        private Integer count;

        public ProductOrderUnitResponse(Long id, ProductOrderUnitStatus productOrderUnitStatus, Long price, Integer count) {
            this.id = id;
            this.productOrderUnitStatus = productOrderUnitStatus;
            this.price = price;
            this.count = count;
        }
    }
}
