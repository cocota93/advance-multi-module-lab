package org.jedy.productOrder.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.member.domain.Member;
import org.jedy.member.exception.MemberNotFindException;
import org.jedy.member.repository.MemberRepository;
import org.jedy.payment.domain.Payment;
import org.jedy.payment.repository.PaymentRepository;
import org.jedy.product.domain.Product;
import org.jedy.product.repository.ProductRepository;
import org.jedy.productOrder.domain.ProductOrder;
import org.jedy.productOrder.domain.ProductOrderStatus;
import org.jedy.productOrder.dto.request.ProductOrderRequest;
import org.jedy.productOrder.dto.request.ProductOrderSearchCondition;
import org.jedy.productOrder.dto.response.ProductOrderDetailResponse;
import org.jedy.productOrder.dto.response.ProductOrderSimpleResponse;
import org.jedy.productOrder.repository.ProductOrderRepository;
import org.jedy.productOrderUnit.domain.ProductOrderUnit;
import org.jedy.productOrderUnit.domain.ProductOrderUnitStatus;
import org.jedy.productOrderUnit.repository.ProductOrderUnitRepository;
import org.jedy.system_core.global.error.exception.BusinessException;
import org.jedy.system_core.global.error.exception.EntityNotFoundException;
import org.jedy.system_core.global.error.exception.ErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class ProductOrderService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ProductOrderRepository productOrderRepository;
    private final ProductOrderUnitRepository productOrderUnitRepository;
    private final PaymentRepository paymentRepository;

    public void add(String username, ProductOrderRequest productOrderRequest, Long paymentId) {
        Member buyer = memberRepository.findByLoginId(username)
                                       .orElseThrow(() -> new BusinessException("구매자를 찾지 못했습니다(" + username + ")", ErrorCode.ENTITY_NOT_FOUND));
        Payment payment = paymentRepository.findById(paymentId)
                                           .orElseThrow(() -> new BusinessException("결제정보를 찾지 못했습니다(" + paymentId + ")", ErrorCode.ENTITY_NOT_FOUND));


        ProductOrder productOrder = ProductOrder.builder()
                                                .member(buyer)
                                                .payment(payment)
                                                .address(productOrderRequest.getReceiverAddress().toAddress())
                                                .orderStatus(ProductOrderStatus.ORDER)
//                    .deliveryStatus()
                                                .build();
        productOrderRepository.save(productOrder);
        payment.assignProductOrder(productOrder);

        for (ProductOrderRequest.ProductOrderUnitReq productOrderUnitReq : productOrderRequest.getOrderUnitReqList()) {
            Product product = productRepository.findById(productOrderUnitReq.getProductId())
                                               .orElseThrow(() -> new BusinessException("존재하지 않는 물품입니다.." + productOrderUnitReq.getProductId(), ErrorCode.ENTITY_NOT_FOUND));
            ProductOrderUnit productOrderUnit = ProductOrderUnit.builder()
                                                                .productOrder(productOrder)
                                                                .product(product)
                                                                .price(product.getPrice())
                                                                .count(productOrderUnitReq.getCount())
                                                                .productOrderUnitStatus(ProductOrderUnitStatus.ORDER)
                                                                .build();
            productOrderUnitRepository.save(productOrderUnit);
        }
    }

    public Page<ProductOrderSimpleResponse> simpleSearch(String loginId, ProductOrderSearchCondition searchCondition, Pageable pageable) {
        return productOrderRepository.simpleSearch(loginId, searchCondition, pageable);
    }

    public ProductOrderDetailResponse detailSearch(String loginId, ProductOrderSearchCondition searchCondition) {
        Member requestMember = memberRepository.findByLoginId(loginId)
                                               .orElseThrow(() -> new MemberNotFindException(loginId));

        ProductOrder requestTarget = productOrderRepository.findByProductOrderUnitAndMember(searchCondition.getProductOrderId(), requestMember.getId())
                                                           .orElseThrow(() -> new EntityNotFoundException("product order not found " + searchCondition.getProductOrderId()));
        ProductOrderDetailResponse detailResponse = ProductOrderDetailResponse.builder()
                                                                              .id(requestTarget.getId())
//                                  .totalPrice()
                                                                              .deliveryStatus(requestTarget.getDeliveryStatus())
                                                                              .orderStatus(requestTarget.getOrderStatus())
                                                                              .address(requestTarget.getAddress())
                                                                              .productOrderUnits(requestTarget.getProductOrderUnits())
                                                                              .payment(requestTarget.getPayment())
                                                                              .build();

        return detailResponse;
    }

}

