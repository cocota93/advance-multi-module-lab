package org.jedy.productOrder.repository;

import org.jedy.productOrder.dto.request.ProductOrderSearchCondition;
import org.jedy.productOrder.dto.response.ProductOrderSimpleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductOrderRepositoryCustom  {

    Page<ProductOrderSimpleResponse> simpleSearch(String loginId, ProductOrderSearchCondition searchCondition, Pageable pageable);
}
