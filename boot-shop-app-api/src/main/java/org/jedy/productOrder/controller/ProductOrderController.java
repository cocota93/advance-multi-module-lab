package org.jedy.productOrder.controller;

import lombok.RequiredArgsConstructor;
import org.jedy.payment.service.PaymentService;
import org.jedy.productOrder.domain.ProductOrderStatus;
import org.jedy.productOrder.dto.request.ProductOrderRequest;
import org.jedy.productOrder.dto.request.ProductOrderSearchCondition;
import org.jedy.productOrder.dto.response.ProductOrderDetailResponse;
import org.jedy.productOrder.dto.response.ProductOrderSimpleResponse;
import org.jedy.productOrder.service.ProductOrderCalculateService;
import org.jedy.productOrder.service.ProductOrderService;
import org.jedy.system_core.entity.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
        Long paymentId = paymentService.pay(productOrderRequest.getPaymentType(), totalPrice);

        //! add라는 네이밍은 모호한것 같음. RegisterReceipt 이런게 낫지않을까?
        productOrderService.add(userDetails.getUsername(), productOrderRequest, paymentId);

        //배달처리용 서비스클래스 하나 따로 만들기
        return ResponseEntity.ok().build();
    }

    //구매한 상품리스트
    @GetMapping(value = "/simpleSearch")
    public PageResponse<ProductOrderSimpleResponse> simpleSearch(@AuthenticationPrincipal UserDetails userDetails, ProductOrderSearchCondition searchCondition, Pageable pageable) {
        Page<ProductOrderSimpleResponse> page = productOrderService.simpleSearch(userDetails.getUsername(), searchCondition, pageable);
        PageResponse<ProductOrderSimpleResponse> pageResponse = new PageResponse<>(page.getContent(), page.getPageable(), page.getTotalElements());
        return pageResponse;
    }

    @GetMapping(value = "/detailSearch")
    public ProductOrderDetailResponse detailSearch(@AuthenticationPrincipal UserDetails userDetails, ProductOrderSearchCondition searchCondition) {
        ProductOrderDetailResponse response = productOrderService.detailSearch(userDetails.getUsername(), searchCondition);
        return response;
    }

    @GetMapping(value = "/list/{productOrderStatus}")
    public boolean getList(@PathVariable("productOrderStatus") ProductOrderStatus productOrderStatus){
        System.out.println("productOrderStatus = " + productOrderStatus);
        return true;
    }



    //부분취소
    //모두취소

}
