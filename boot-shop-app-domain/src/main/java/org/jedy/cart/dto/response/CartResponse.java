package org.jedy.cart.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import org.jedy.member.domain.Member;
import org.jedy.product.domain.Product;
import org.jedy.product.dto.response.ProductSearchResponse;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import static javax.persistence.FetchType.LAZY;

@Data
public class CartResponse {
    private Long id;
    private ProductSearchResponse product;
    private int count;

    @QueryProjection
    public CartResponse(Long id, Product product, Integer count) {
        this.id = id;
        this.product = new ProductSearchResponse(product.getId(), product.getUpperCatCd(), product.getName(), product.getPrice());
        this.count = count;
    }
}
