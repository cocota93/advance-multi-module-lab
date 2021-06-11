package org.jedy.notice.repository.query;

import org.jedy.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeQueryRepository extends JpaRepository<Notice, Long>, NoticeQueryRepositoryCustom {

}
