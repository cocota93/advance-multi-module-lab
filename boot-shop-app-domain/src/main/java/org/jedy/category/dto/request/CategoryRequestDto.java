package org.jedy.category.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CategoryRequestDto {

    @NotBlank(message = "카테고리 코드를 입력해주세요")
    @Size(max = 10)
    private String catCd;

    @NotBlank
    @Size(max = 50)
    private String catName;

    @NotBlank
    @Size(max = 10)
    private String upperCatCd;

//    @NotBlank
//    @Size(max = 11)
//    private Integer catLv;

}
