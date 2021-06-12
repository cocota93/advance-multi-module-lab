package org.jedy.member.service;

import lombok.RequiredArgsConstructor;
import org.jedy.member.dto.MemberResponse;
import org.jedy.member.dto.MemberSearchCondition;
import org.jedy.member.repository.query.MemberQueryRepositoryImpl;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberPagingServiceImpl {

    private final MemberQueryRepositoryImpl memberQueryRepository;

    public Page<MemberResponse> searchPaging(MemberSearchCondition condition, Integer page, Integer size) {
        return memberQueryRepository.searchPageComplex(condition,page,size);
    }

}
