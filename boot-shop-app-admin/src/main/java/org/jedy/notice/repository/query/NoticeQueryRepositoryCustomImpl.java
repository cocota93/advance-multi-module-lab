package org.jedy.notice.repository.query;


import com.querydsl.core.types.dsl.BooleanExpression;
import org.jedy.notice.dto.NoticeSearchCondition;
import org.jedy.notice.domain.Notice;
import org.jedy.system_core.support.Querydsl4RepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.jedy.notice.domain.QNotice.notice;

public class NoticeQueryRepositoryCustomImpl extends Querydsl4RepositorySupport implements NoticeQueryRepositoryCustom {

    public NoticeQueryRepositoryCustomImpl() {
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
