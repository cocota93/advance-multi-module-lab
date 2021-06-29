package org.jedy.payment.domain;

import lombok.*;
import org.jedy.productOrder.domain.ProductOrder;
import org.jedy.system_core.entity.BaseTimeEntity;
import org.jedy.system_core.global.error.exception.BusinessException;
import org.jedy.system_core.global.error.exception.ErrorCode;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column
    private Long price;

    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
    private ProductOrder productOrder;

    public void assignProductOrder(ProductOrder productOrder) {
        if(this.productOrder != null && this.productOrder.getId() != productOrder.getId()){
            throw new BusinessException("결제자를 중간에 변경할수는 없습니다.(" + this.productOrder.getId() + " -> " + productOrder.getId() +  ")"
            , ErrorCode.INTERNAL_SERVER_ERROR);
        }

        this.productOrder = productOrder;
    }
}
