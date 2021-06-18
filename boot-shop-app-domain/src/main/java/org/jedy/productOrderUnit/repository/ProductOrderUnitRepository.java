package org.jedy.productOrderUnit.repository;

import org.jedy.productOrderUnit.domain.ProductOrderUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderUnitRepository extends JpaRepository<ProductOrderUnit, Long> {
}