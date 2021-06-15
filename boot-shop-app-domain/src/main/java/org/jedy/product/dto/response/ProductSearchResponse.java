package org.jedy.product.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductSearchResponse {

    private Long id;
    private String upperCatCd;
    private String name;
    private Long price;

    @QueryProjection
    public ProductSearchResponse(Long id, String upperCatCd, String name, Long price) {
        this.id = id;
        this.upperCatCd = upperCatCd;
        this.name = name;
        this.price = price;
    }
}
