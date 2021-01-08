package org.jedy.notice_core.repository;

import org.jedy.notice_core.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

//    public List<Notice> basicSelect() {
//        return  select()
//    }
}
