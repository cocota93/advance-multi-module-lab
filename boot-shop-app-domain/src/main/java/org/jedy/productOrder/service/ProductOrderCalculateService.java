package org.jedy.productOrder.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.product.domain.Product;
import org.jedy.product.repository.ProductRepository;
import org.jedy.productOrder.dto.request.ProductOrderRequest;
import org.jedy.system_core.global.error.exception.BusinessException;
import org.jedy.system_core.global.error.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class ProductOrderCalculateService {

    private final ProductRepository productRepository;

    public long calculate(@Valid List<ProductOrderRequest.ProductOrderUnitReq> productOrderRequest) {
        long totalPrice = 0;
        for (ProductOrderRequest.ProductOrderUnitReq productOrderUnitReq : productOrderRequest) {
            Product product = productRepository.findById(productOrderUnitReq.getProductId()).orElseThrow(() -> new BusinessException("존재하지 않는 상품입니다", ErrorCode.ENTITY_NOT_FOUND));
            long price = product.getPrice() * productOrderUnitReq.getCount();
            totalPrice += price;
        }

        return totalPrice;
    }
}

