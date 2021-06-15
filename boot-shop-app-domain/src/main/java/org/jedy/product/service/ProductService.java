package org.jedy.product.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jedy.product.domain.Product;
import org.jedy.product.dto.request.ProductRequestDto;
import org.jedy.product.dto.request.ProductSearchCondition;
import org.jedy.product.dto.response.ProductSearchResponse;
import org.jedy.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductService  {

    private final ProductRepository productRepository;

    public Long addProduct(ProductRequestDto productRequestDto) {
        Product save = productRepository.save(Product.builder()
                                                     .name(productRequestDto.getName())
                                                     .price(productRequestDto.getPrice())
                                                     .upperCatCd(productRequestDto.getUpperCatCd())
                                                     .build()
        );
        return save.getId();
    }


    public Page<ProductSearchResponse> search(ProductSearchCondition productSearchCondition, Pageable pageable) {
        return productRepository.search(productSearchCondition, pageable);
    }
}
