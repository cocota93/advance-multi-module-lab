package org.jedy.product.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ProductSearchCondition {
    private String upperCatCd;
    private List<String> productName;
}
