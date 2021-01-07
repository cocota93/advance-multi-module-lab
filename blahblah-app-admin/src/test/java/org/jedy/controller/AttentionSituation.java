//package org.jedy.controller;
//
//import org.jedy.member_core.domain.Member;
//import org.jedy.member_core.repository.MemberRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.annotation.Commit;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class AttentionSituation {
//
//    @Autowired MemberRepository memberRepository;
//    @Autowired PasswordEncoder passwordEncoder;
//    @Autowired EntityManager entityManager;
//
//    @Test
////    @Commit
//    public void lazyExecuteTest(){
////        Member member = Member.BySignup()
////                .loginId("ryong")
////                .password(passwordEncoder.encode("1234"))
////                .age(28)
////                .email("cocota93@gmail.com")
////                .name("ryong")
////                .build();
////
////        memberRepository.save(member);
////
////
////        member.setAge(1);
////
////        Member member1 = memberRepository.findById(1L).get();
//////        Member member1 = memberRepository.findAll().get(0);
////        //1차 캐쉬에서 조회 시도 및 성공. 쿼리는 안나감. 1차 캐쉬에는 키값과 엔터티로 맵핑되어 저장되기 떄문에 키값을 이용한 조회일때만 쿼리가 안나감.
////        assertEquals("ryong", member1.getName());
////
////        //jedy가 디비에 플러쉬 됨
////        memberRepository.findByLoginId("something");
//    }
//
//
//
//}