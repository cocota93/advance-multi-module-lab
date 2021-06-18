package org.jedy.productOrder.repository;

import org.jedy.productOrder.domain.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long>, ProductOrderRepositoryCustom {
    @Query("select po from ProductOrder po join fetch po.member m join fetch po.productOrderUnits pou where po.id = :productOrderId and m.id = :memberId ")
    Optional<ProductOrder> findByProductOrderUnitAndMember(@Param("productOrderId") Long productOrderId, @Param("memberId") Long memberId);
}