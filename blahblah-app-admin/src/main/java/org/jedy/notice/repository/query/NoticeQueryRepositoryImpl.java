package org.jedy.notice.repository.query;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.jedy.member.dto.MemberResponse;
import org.jedy.member.dto.MemberSearchCondition;
import org.jedy.member.dto.QMemberResponse;
import org.jedy.member.repository.query.MemberQueryRepositoryCustom;
import org.jedy.member_core.domain.Member;
import org.jedy.notice.dto.NoticeSearchCondition;
import org.jedy.notice_core.domain.Notice;
import org.jedy.notice_core.domain.QNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static org.jedy.member_core.domain.QMember.member;
import static org.jedy.notice_core.domain.QNotice.notice;
import static org.springframework.util.StringUtils.hasText;

public class NoticeQueryRepositoryImpl implements NoticeQueryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public NoticeQueryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<Notice> searchPage(NoticeSearchCondition condition, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        List<Notice> content = queryFactory
                .select(notice)
                .from(notice)
                .where(
                        deletedEq(condition.getDeleted())
                )
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();

        JPAQuery<Notice> countQuery = queryFactory
                .select(notice)
                .from(notice)
                .where(
                        deletedEq(condition.getDeleted())
                );

        Page<Notice> pageList = PageableExecutionUtils.getPage(content, pageRequest, countQuery::fetchCount);

        return pageList;
    }


    private BooleanExpression deletedEq(Boolean deleted) {
        return deleted != null ? notice.deleted.eq(deleted) : null;
    }



}
