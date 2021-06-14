package org.jedy.member.repository.query;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.jedy.member.dto.MemberResponse;
import org.jedy.member.dto.MemberSearchCondition;
import org.jedy.member.domain.Member;
import org.jedy.member.dto.QMemberResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static org.jedy.member.domain.QMember.member;
import static org.springframework.util.StringUtils.hasText;

public class MemberQueryRepositoryCustomImpl implements MemberQueryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberQueryRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MemberResponse> search(MemberSearchCondition condition) {
        return queryFactory
                .select(new QMemberResponse(
                        member.loginId,
                        member.name,
                        member.email,
                        member.age
                        )
                )
                .from(member)
                .where(
                        usernameEq(condition.getName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe())
                )
                .fetch();
    }

    @Override
    public Page<MemberResponse> searchPageComplex(MemberSearchCondition condition, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        List<MemberResponse> content = queryFactory
                .select(new QMemberResponse(
                        member.loginId,
                        member.name,
                        member.email,
                        member.age
                        )
                )
                .from(member)
                .where(
                        usernameEq(condition.getName())
                )
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();

        JPAQuery<Member> countQuery = queryFactory
                .select(member)
                .from(member)
                .where(
                        usernameEq(condition.getName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe())
                );

        Page<MemberResponse> pageList = PageableExecutionUtils.getPage(content, pageRequest, countQuery::fetchCount);

        return pageList;
    }


    private BooleanExpression usernameEq(String name) {
        return hasText(name) ? member.name.eq(name) : null;
    }


    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe != null ? member.age.goe(ageGoe) : null;

    }

    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null;

    }
}
