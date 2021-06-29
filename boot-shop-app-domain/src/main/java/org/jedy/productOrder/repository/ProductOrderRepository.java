package org.jedy.productOrder.repository;

import org.jedy.productOrder.domain.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long>, ProductOrderRepositoryCustom {
    @Query("SELECT po FROM ProductOrder po JOIN FETCH po.member m JOIN FETCH po.productOrderUnits pou JOIN FETCH po.payment pay where po.id = :productOrderId and m.id = :memberId ")
    Optional<ProductOrder> findByProductOrderUnitAndMember(@Param("productOrderId") Long productOrderId, @Param("memberId") Long memberId);
}