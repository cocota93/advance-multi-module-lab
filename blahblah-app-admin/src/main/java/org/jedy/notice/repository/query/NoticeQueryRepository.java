package org.jedy.notice.repository.query;

import org.jedy.member.dto.MemberResponse;
import org.jedy.member.repository.query.MemberQueryRepositoryCustom;
import org.jedy.member_core.domain.Member;
import org.jedy.notice_core.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeQueryRepository extends JpaRepository<Notice, Long>, NoticeQueryRepositoryCustom {

}
