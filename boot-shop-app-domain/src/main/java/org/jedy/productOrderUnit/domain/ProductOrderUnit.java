package org.jedy.productOrderUnit.domain;

import lombok.*;
import org.jedy.productOrder.domain.ProductOrder;
import org.jedy.system_core.entity.BaseTimeEntity;

import javax.persistence.*;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOrderUnit extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_order_unit_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductOrderUnitStatus productOrderUnitStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_order_id")
    private ProductOrder productOrder;




    /** 구매당시 1개당 가격 */
    private Long price;
    private Integer count;
}