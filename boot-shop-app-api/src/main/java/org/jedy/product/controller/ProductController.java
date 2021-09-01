package org.jedy.product.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.jedy.product.dto.request.ProductSearchCondition;
import org.jedy.product.dto.response.ProductSearchResponse;
import org.jedy.product.service.ProductService;
import org.jedy.system_core.global.error.exception.BusinessException;
import org.jedy.system_core.global.error.exception.ErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping(value = "/exist")
    public boolean exist(String productName){
        return productService.exist(productName);
    }

    @PostMapping(value = "/apiTestSuccess")
    public Page apiTestSuccess(@RequestBody ProductSearchCondition productSearchCondition, Pageable pageable) {
        Page<ProductSearchResponse> search = productService.search(productSearchCondition, pageable);
        return search;
    }

    @PostMapping(value = "/apiTestFail")
    public Page apiTestFail(@RequestBody ProductSearchCondition productSearchCondition, Pageable pageable) {
        throw new BusinessException("테스트예외", ErrorCode.INVALID_INPUT_VALUE);
//        throw new MethodArgumentNotValidException()
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

//        Page<ProductSearchResponse> search = productService.search(productSearchCondition, pageable);
//        return search;

//        return null;
    }

}
