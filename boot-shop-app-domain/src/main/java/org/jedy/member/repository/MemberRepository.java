package org.jedy.member.repository;


import org.jedy.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m join fetch m.authorityList auth where m.loginId = :loginId")
    Optional<Member> findFetchAuthByLoginId(@Param("loginId") String loginId);


    Optional<Member> findByLoginId(String loginId);


}
