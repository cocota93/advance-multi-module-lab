package org.jedy.productOrder.domain;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductOrderEnumConverter implements Converter<String, ProductOrderStatus> {
    @Override
    public ProductOrderStatus convert(String source) {
        return ProductOrderStatus.valueOf(source.toUpperCase());
    }
}
