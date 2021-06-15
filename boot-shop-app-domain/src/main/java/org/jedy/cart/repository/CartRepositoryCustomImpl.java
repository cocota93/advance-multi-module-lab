package org.jedy.cart.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.jedy.cart.domain.Cart;
import org.jedy.cart.dto.response.CartResponse;
import org.jedy.cart.dto.response.QCartResponse;
import org.jedy.cart.exception.EmptyCartNotFindException;
import org.jedy.system_core.support.Querydsl4RepositorySupport;

import java.util.List;
import java.util.Optional;

import static org.jedy.cart.domain.QCart.cart;
import static org.jedy.member.domain.QMember.member;
import static org.jedy.product.domain.QProduct.product;

public class CartRepositoryCustomImpl extends Querydsl4RepositorySupport implements CartRepositoryCustom {
    public CartRepositoryCustomImpl() {
        super(Cart.class);
    }

    @Override
    public List<CartResponse> findUseCartList(String memberLoginId, Boolean use) {
//      @QueryProejection사용하여 dto조회시 페치조인과 일반조인 https://irerin07.tistory.com/144
        return select(
                new QCartResponse(
                    cart.id, cart.product, cart.count
                )
        ).from(cart)
         .join(cart.member, member)
         .leftJoin(cart.product, product)
         .where(
                 memberLoginIdEq(memberLoginId),
                 useEq(use)
         )
         .fetch();
    }

    @Override
    public Cart findEmptyCart(String memberLoginId, Boolean use) {
//      지연로딩된 엔터티가 null인채로 반환할경우 json으로 직렬화 불가.
//      일반조인으로 해결하던가 무시하는 옵션을 켜야한다
//      https://velog.io/@agugu95/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-JPA-Restful-API-%EA%B5%AC%EC%B6%95-API%EC%99%80-%EC%84%B1%EB%8A%A5-%EC%B5%9C%EC%A0%81%ED%99%94-2%ED%8E%B8
//      https://jhkang-tech.tistory.com/92zzz
        Cart emptyCart =
            select(
                    cart
            ).from(cart)
             .join(cart.member, member).fetchJoin()
             .leftJoin(cart.product, product).fetchJoin()
             .where(
                     memberLoginIdEq(memberLoginId),
                     useEq(use)
             )
             .limit(1)
             .fetchOne();
        return Optional.of(emptyCart).orElseThrow(() -> new EmptyCartNotFindException(memberLoginId));
    }

    private BooleanExpression memberLoginIdEq(String memberLoginId) {
        return memberLoginId != null ? cart.member.loginId.eq(memberLoginId) : null;
    }

    private BooleanExpression useEq(Boolean use) {
        return use != null ? cart.use.eq(use) : null;
    }


}
