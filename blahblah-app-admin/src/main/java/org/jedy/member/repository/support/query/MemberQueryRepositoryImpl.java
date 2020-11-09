package org.jedy.member.repository.support.query;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.jedy.member.dto.MemberResponse;
import org.jedy.member.dto.MemberSearchCondition;
import org.jedy.member.dto.QMemberResponse;
import org.jedy.member_core.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static org.jedy.member_core.domain.QMember.member;
import static org.springframework.util.StringUtils.hasText;

public class MemberQueryRepositoryImpl implements MemberQueryRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MemberQueryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MemberResponse> search(MemberSearchCondition condition) {
        return queryFactory
                .select(new QMemberResponse(
                        member.name
                        )
                )
                .from(member)
                .where(
                        usernameEq(condition.getName())
                )
                .fetch();
    }

    @Override
    public Page<MemberResponse> searchPageComplex(MemberSearchCondition condition, Pageable pageable) {
        List<MemberResponse> content = queryFactory
                .select(new QMemberResponse(
                                member.name
                        )
                )
                .from(member)
                .where(
                        usernameEq(condition.getName())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Member> countQuery = queryFactory
                .select(member)
                .from(member)
                .where(
                        usernameEq(condition.getName())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }


    private BooleanExpression usernameEq(String name) {
        return hasText(name) ? member.name.eq(name) : null;
    }

}
