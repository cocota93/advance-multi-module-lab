package org.jedy.productOrder.dto.request;

import lombok.Data;
import org.jedy.address.Address;
import org.jedy.payment.domain.Payment;
import org.jedy.payment.domain.PaymentType;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ProductOrderRequest {

    @NotBlank
    @Size(min = 1, max = 10)
    private String receiverName;

    @Valid
    private AddressRequest receiverAddress;

    @Valid
    private PhoneRequest receiverPhone;

    @Valid
    private List<ProductOrderUnitReq> orderUnitReqList;
    //List<coupon> 쿠폰 사용 리스트

    //결제수단
    private PaymentType paymentType;


    @Data
    static public class ProductOrderUnitReq {
        @NotNull
        @Min(value = 1)
        private Long productId;

        @Min(value = 1)
        private int count;
    }

    @Data
    static public class AddressRequest {
        @NotBlank
        private String city;
        @NotBlank
        private String street;
        @NotBlank
        private String zipcode;

        public Address toAddress() {
            return new Address(city,street,zipcode);
        }
    }

    @Data
    static public class PhoneRequest {
        @NotBlank
        @Size(min = 3, max = 3)
        String head;

        @NotBlank
        @Size(min = 3, max = 4)
        String body;

        @NotBlank
        @Size(min = 4, max = 4)
        String tail;
    }
}
