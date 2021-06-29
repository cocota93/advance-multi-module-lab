package org.jedy.productOrder.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.jedy.productOrder.domain.ProductOrder;
import org.jedy.productOrder.dto.request.ProductOrderSearchCondition;
import org.jedy.productOrder.dto.response.ProductOrderSimpleResponse;
import org.jedy.productOrder.dto.response.QProductOrderSimpleResponse;
import org.jedy.system_core.support.Querydsl4RepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.jedy.productOrder.domain.QProductOrder.productOrder;


public class ProductOrderRepositoryCustomImpl extends Querydsl4RepositorySupport implements ProductOrderRepositoryCustom {
    public ProductOrderRepositoryCustomImpl() { super(ProductOrder.class); }

    @Override
    public Page<ProductOrderSimpleResponse> simpleSearch(String loginId, ProductOrderSearchCondition searchCondition, Pageable pageable) {
        return applyPagination(pageable, query -> query
                .select(
                        new QProductOrderSimpleResponse(
                                productOrder.id,
                                productOrder.orderStatus,
                                productOrder.totalPrice,
                                productOrder.deliveryStatus,
                                productOrder.address,
                                productOrder.payment
                        )
                )
                .from(productOrder)
                .where(
                        createDateCompare(searchCondition.getSearchStart(), searchCondition.getSearchEnd())
                )
        );
    }


    private BooleanExpression createDateCompare(LocalDateTime searchStart, LocalDateTime searchEnd) {
        return productOrder.createDate.between(searchStart, searchEnd);
    }

    private BooleanExpression createDateCompare(LocalDate searchStart, LocalDate searchEnd) {
        return productOrder.createDate.between(
                searchStart != null ? searchStart.atStartOfDay() : null,
                searchEnd != null ? searchEnd.atStartOfDay() : null
        );
    }
}
