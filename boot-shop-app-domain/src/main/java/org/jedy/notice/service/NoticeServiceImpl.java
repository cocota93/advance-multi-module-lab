package org.jedy.notice.service;

import lombok.RequiredArgsConstructor;
import org.jedy.notice.domain.Notice;
import org.jedy.notice.repository.NoticeRepository;
import org.jedy.system_core.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeServiceImpl {

    private final NoticeRepository noticeRepository;

    public Long create(String content){
        Notice notice = new Notice(content);
        return noticeRepository.save(notice).getId();
    }

    public void modify(Long noticeId, String content){
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new EntityNotFoundException("이미 삭제된 글을 수정 시도했습니다. noticeId : " + noticeId));
        notice.setContent(content);
    }

    public void delete(Long noticeId){
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new EntityNotFoundException("이미 삭제된 글을 삭제 시도했습니다. noticeId : " + noticeId));
        notice.delete();
    }

    public Notice findById(Long noticeId){
        return noticeRepository.findById(noticeId).orElseThrow(() -> new EntityNotFoundException("이미 삭제된 글을 읽으려 시도합니다. noticeId : " + noticeId));
    }

}
