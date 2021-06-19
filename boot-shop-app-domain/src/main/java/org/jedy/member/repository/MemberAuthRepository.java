package org.jedy.member.repository;

import org.jedy.member.domain.MemberAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAuthRepository extends JpaRepository<MemberAuth, Long> {
}