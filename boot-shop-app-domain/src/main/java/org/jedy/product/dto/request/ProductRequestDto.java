package org.jedy.product.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProductRequestDto {

    @NotBlank(message = "상품명을 작성하세요.")
    @Size(max = 100, message = "상품명을 알맞게 작성해주세요.")
    private String name;

    @NotBlank(message = "상위 카테고리를 작성하세요.")
    @Size(max = 10, message = "상위 카테고리를 알맞게 작성해주세요.")
    private String upperCatCd;

    @NotNull(message = "상품 가격을 작성하세요.")
    private Long price;
}
