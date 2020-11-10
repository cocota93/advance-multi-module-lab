package org.jedy.notice_core.repository;

import org.jedy.notice_core.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
