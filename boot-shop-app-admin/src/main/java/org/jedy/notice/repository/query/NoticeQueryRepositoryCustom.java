package org.jedy.notice.repository.query;

import org.jedy.notice.dto.NoticeSearchCondition;
import org.jedy.notice.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeQueryRepositoryCustom {
    Page<Notice> searchPage(NoticeSearchCondition condition, Pageable pageable);
}
