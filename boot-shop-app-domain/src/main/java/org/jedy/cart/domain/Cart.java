package org.jedy.cart.domain;

import lombok.*;
import org.jedy.member.domain.Member;
import org.jedy.product.domain.Product;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;


/*
 * 주문이 이루어져서 삭제되어야할때 실제로 삭제하는게 나을려나? 아니면 플래그만 세워두고
 * 나중에 재사용하는게 나을려나?
 * -> 검색을 해보니 대부분 장바구니에 담을수 있는 개수를 제한 하는것 같다.
 * 플래그만 두고 재사용 하는 방향으로 해야겠다.
 * */

/*
* 처음 생각했을때는 장바구니(cart)와 여러물품으로 구성된 cartUnit 두개가 필요하다 생각했는데
* 생각보다 용도가 불분명하고 있어야할 이유가 없음.
* */

/**
 * 유저의 장바구니.
 * 유저 한명당 하나의 장바구니를 소유한다.
 * 장바구니는 원하는 물품들은 모아두었다가 일부 or 전체 구매요청을 하기 쉽도록 하는 역할만 한다.
 * 유저가 어떤 기기로 로그인하든 똑같은 장바구니 내역을 볼수 있도록 도와줄뿐
 * 실제 주문과는 관련되어있지 않는다.
 * */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@EntityListeners(AuditingEntityListener.class)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Long id;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

//    @LastModifiedDate
//    private LocalDateTime lastModifiedDate;

    @Column
    private boolean useSlot;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private int count;

    public void changeProduct(Product product, int count) {
        this.product = product;
        this.count = count;
        this.useSlot = true;
    }

    @Builder(builderClassName = "InitAssignBySignup", builderMethodName = "InitAssignBySignup")
    public Cart(Member member) {
        this.member = member;
    }


    public boolean equalOwner(String loginId) {
        if(member == null) return false;
        if(!member.EqualLoginId(loginId)) return false;
        return true;
    }

    public void disableSlot() {
        useSlot = false;
    }
}