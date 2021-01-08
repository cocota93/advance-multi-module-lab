package org.jedy.notice.repository.query;

import org.jedy.member.dto.MemberResponse;
import org.jedy.member.dto.MemberSearchCondition;
import org.jedy.notice.dto.NoticeSearchCondition;
import org.jedy.notice_core.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeQueryRepositoryCustom {
    Page<Notice> searchPage(NoticeSearchCondition condition, Pageable pageable);
}
