package org.jedy.productOrder.controller;

import lombok.RequiredArgsConstructor;
import org.jedy.cart.dto.request.CartAddRequest;
import org.jedy.payment.service.PaymentService;
import org.jedy.productOrder.dto.request.ProductOrderRequest;
import org.jedy.productOrder.service.ProductOrderCalculateService;
import org.jedy.productOrder.service.ProductOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/productOrder")
@RequiredArgsConstructor
public class ProductOrderController {

    private final ProductOrderService productOrderService;
    private final ProductOrderCalculateService productOrderCalculateService;
    private final PaymentService paymentService;

    @PostMapping(value = "/add")
    public ResponseEntity<Void> add(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody ProductOrderRequest productOrderRequest) {
        long totalPrice = productOrderCalculateService.calculate(productOrderRequest.getOrderUnitReqList());
        paymentService.pay(productOrderRequest.getPaymentType(), totalPrice);

        //! add라는 네이밍은 모호한것 같음. RegisterReceipt 이런게 낫지않을까?
        productOrderService.add(userDetails.getUsername(), productOrderRequest);

        //배달처리용 서비스클래스 하나 따로 만들기
        return ResponseEntity.ok().build();
    }

    //구매한 상품리스트

    //부분취소
    //모두취소

}
