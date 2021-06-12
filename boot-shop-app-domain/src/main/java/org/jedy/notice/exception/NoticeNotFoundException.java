package org.jedy.notice.exception;

import org.jedy.system_core.global.error.exception.EntityNotFoundException;

public class NoticeNotFoundException extends EntityNotFoundException {


    public NoticeNotFoundException(Long noticeId) {
        super("NoticeId : " + noticeId + ", 존재하지 않는 글을 읽으려 시도합니다");
    }
}
