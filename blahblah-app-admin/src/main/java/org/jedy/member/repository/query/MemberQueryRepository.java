package org.jedy.member.repository.query;

import org.jedy.member.dto.MemberResponse;
import org.jedy.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberQueryRepository extends JpaRepository<Member, Long>, MemberQueryRepositoryCustom {

    Member findByLoginId(String loginId);

    @Query("select m from Member m where m.name = :name ")
    List<Member> findNameMemberList(@Param("name") String name);

    @Query("select m.name from Member m")
    List<String> findNameList();

    @Query("select new org.jedy.member.dto.MemberResponse(m.loginId, m.name, m.email, m.age) from Member m ")
    List<MemberResponse> findMemberDto();

}
