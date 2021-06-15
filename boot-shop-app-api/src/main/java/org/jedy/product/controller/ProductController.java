package org.jedy.product.controller;

import lombok.RequiredArgsConstructor;
import org.jedy.product.dto.request.ProductSearchCondition;
import org.jedy.product.dto.response.ProductSearchResponse;
import org.jedy.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page search(ProductSearchCondition productSearchCondition, Pageable pageable) {
        Page<ProductSearchResponse> search = productService.search(productSearchCondition, pageable);
        return search;
    }

}
