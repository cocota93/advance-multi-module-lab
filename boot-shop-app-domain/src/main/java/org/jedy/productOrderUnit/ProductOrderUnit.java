package org.jedy.productOrderUnit;

import lombok.Getter;
import org.jedy.productOrder.ProductOrder;
import org.jedy.system_core.entity.BaseTimeEntity;

import javax.persistence.*;


@Entity
@Getter
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
    private Long totalPrice;
}