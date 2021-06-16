package org.jedy.productOrder.repository;

import org.jedy.productOrder.domain.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long>, ProductOrderRepositoryCustom {


}