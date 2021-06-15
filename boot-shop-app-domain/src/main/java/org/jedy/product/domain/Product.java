package org.jedy.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jedy.system_core.entity.BaseTimeEntity;

import javax.persistence.*;

/*
 * 판매된 개수를 객체가 가지고 있는게 맞는걸까?
 * 판매가능 개수를 객체가 가지고있는게 맞는걸까?
 * 주로 통계를 낼떄 사용될텐데 나중에 배치서버 만들면 거기서 관리하는게 맞지않을려나?
 */


/**
 * 상품 자체에 대한 정보.
 * */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Column(length = 10)
    private String upperCatCd;

    @Column(length = 100)
    private String name;

    /**
     * 단순 등록 가격. 할인이 적용되지 않은 가격.
     * */
    @Column
    private Long price;
}