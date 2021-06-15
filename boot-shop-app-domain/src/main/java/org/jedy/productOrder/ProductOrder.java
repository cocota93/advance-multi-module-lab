package org.jedy.productOrder;

import lombok.Getter;
import org.jedy.address.Address;
import org.jedy.member.domain.Member;
import org.jedy.productOrderUnit.ProductOrderUnit;
import org.jedy.system_core.entity.BaseTimeEntity;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;


/*
* 주문취소
* 주문 취소를 어떻게 할지가 주요 이슈인것 같다
* 일부상품만 주문 취소 하는게 가능해야할까?
*
* 주식같은경우는 완전취소후 새로 주문이 들어가는 형태
* 쇼핑몰에서도 그런식으로 하는게 맞지않을까?
*
*
* */

/*
* 변하는 정보들
* 부분 취소가 발생하면 총 주문금액은 변한다.
* 음료수를 3개 주문했다가 1개로 변경이 될수도 있다.
* 때문에 변할수 있는 데이터들에 대해서 어떻게 관리해야할지 모르겠다.
* 음...뭐 주문 취소가 발생하면 그때 재계산해서 저장하면 그만일것 같기도하구?
*
* 잠깐 그러면 쿠폰같은것들은 어카지? 3만원 이상일떄만 사용가능한 쿠폰이 사용된채 결제되었다가
* 일부 취소후 3만원 미만이 되었다면?? 너무 복잡해진다. 이런건 무시하자
* */

/**
 * 주문의 현재 상태.
 * 주문 취소가 발생할경우 취소가 된만큼 데이터가 변경된다.
 * 취소가 된 부분에 대해서는 별도의 로그가 남는다.
 * 주문에 대한 세부적인 아이템들은 ProductOrderUnit클래스로 관리된다.
 * */
@Entity
@Getter
public class ProductOrder extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_order_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private ProductOrderStatus orderStatus;

    @OneToMany(mappedBy = "productOrder", cascade = CascadeType.ALL)
    private List<ProductOrderUnit> productOrderUnits;

    @Column
    private Long totalPrice;

    @Enumerated(EnumType.STRING)
    private ProductOrderDeliveryStatus status; //READY, COMP

    @Embedded
    private Address address;
}