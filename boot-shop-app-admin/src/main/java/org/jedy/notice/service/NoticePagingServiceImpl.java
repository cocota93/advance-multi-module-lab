package org.jedy.notice.service;

import lombok.RequiredArgsConstructor;
import org.jedy.notice.domain.Notice;
import org.jedy.notice.dto.NoticeSearchCondition;
import org.jedy.notice.repository.query.NoticeQueryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticePagingServiceImpl {

    private final NoticeQueryRepository noticeQueryRepository;

    public Page<Notice> searchPaging(NoticeSearchCondition condition, Pageable pageable) {
        return noticeQueryRepository.searchPage(condition, pageable);
    }
}
