package org.jedy.payment.domain;

import lombok.Builder;
import lombok.Getter;
import org.jedy.system_core.entity.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@Builder
public class Payment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long id;

    @Column
    private PaymentType paymentType;

    @Column
    private Long price;
}
