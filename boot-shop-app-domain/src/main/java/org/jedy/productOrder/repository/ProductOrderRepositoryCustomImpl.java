package org.jedy.productOrder.repository;

import org.jedy.productOrder.domain.ProductOrder;
import org.jedy.system_core.support.Querydsl4RepositorySupport;

public class ProductOrderRepositoryCustomImpl extends Querydsl4RepositorySupport implements ProductOrderRepositoryCustom {
    public ProductOrderRepositoryCustomImpl() { super(ProductOrder.class); }

}
