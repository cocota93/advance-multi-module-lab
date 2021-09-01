package org.jedy.product.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.jedy.product.domain.Product;
import org.jedy.product.dto.request.ProductSearchCondition;
import org.jedy.product.dto.response.ProductSearchResponse;
import org.jedy.product.dto.response.QProductSearchResponse;
import org.jedy.system_core.support.Querydsl4RepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.function.Supplier;

import static org.jedy.product.domain.QProduct.product;

public class ProductRepositoryCustomImpl extends Querydsl4RepositorySupport implements ProductRepositoryCustom {
    public ProductRepositoryCustomImpl() {
        super(Product.class);
    }

    @Override
    public Page<ProductSearchResponse> search(ProductSearchCondition condition, Pageable pageable) {

        return applyPagination(pageable, query -> query
                .select(
                        new QProductSearchResponse(
                                product.id,
                                product.upperCatCd,
                                product.name,
                                product.price
                        )

                )
                .from(product)
                .where(
                    catCdEq(condition.getUpperCatCd()),
                    equalProductName(condition.getProductName())
                )
        );
    }

    public static BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (IllegalArgumentException e) {
            return new BooleanBuilder();
        }
    }

    private BooleanBuilder equalProductName(List<String> productNameList) {
        if(ObjectUtils.isEmpty(productNameList)) return null;

        BooleanBuilder booleanBuilder = nullSafeBuilder(() -> null);
        for (String name : productNameList) {
            booleanBuilder.or(product.name.eq(name));
        }

        return booleanBuilder;
    }

    private BooleanExpression catCdEq(String upperCatCd) { return upperCatCd != null ? product.upperCatCd.eq(upperCatCd) : null; }
}
