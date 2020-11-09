package org.jedy.member.repository.support.query;

import org.jedy.member.dto.MemberResponse;
import org.jedy.member.dto.MemberSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberQueryRepositoryCustom {
    List<MemberResponse> search(MemberSearchCondition condition);
    Page<MemberResponse> searchPageComplex(MemberSearchCondition condition, Pageable pageable);
}
