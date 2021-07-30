package org.jedy.product.repository;

import org.jedy.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom, QuerydslPredicateExecutor<Product> {
}
