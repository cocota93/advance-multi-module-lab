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
import org.jedy.system_core.support.Querydsl4RepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static org.jedy.member_core.domain.QMember.member;
import static org.jedy.notice_core.domain.QNotice.notice;
import static org.springframework.util.StringUtils.hasText;

public class NoticeQueryRepositoryImpl extends Querydsl4RepositorySupport implements NoticeQueryRepositoryCustom {

    public NoticeQueryRepositoryImpl() {
        super(Notice.class);
    }

    public List<Notice> basicSelect() {
        return select(notice)
                .from(notice)
                .fetch();
    }

    public List<Notice> basicSelectFrom() {
        return selectFrom(notice)
                .fetch();
    }

    @Override
    public Page<Notice> searchPage(NoticeSearchCondition condition, Pageable pageable) {
        return applyPagination(pageable, query -> query
                .selectFrom(notice)
                .where(
                        deletedEq(condition.getDeleted())
                )
        );
    }


    private BooleanExpression deletedEq(Boolean deleted) {
        return deleted != null ? notice.deleted.eq(deleted) : null;
    }



}
