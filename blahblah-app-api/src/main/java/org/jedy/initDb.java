package org.jedy;

import lombok.RequiredArgsConstructor;
import org.jedy.member_core.domain.Member;
import org.jedy.member_core.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class initDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
//        initService.memberInit();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;

        private final PasswordEncoder passwordEncoder;

        private final MemberRepository memberRepository;

        public void memberInit(){
            for(int i = 1; i < 3; ++i){
                Member member = createMember("member", i);
                em.persist(member);
            }
        }

        public void noticeInit(){
//            Notice notice =
        }

        private Member createMember(String aliasName, Integer sequence) {
            Member member = Member.BySignup()
                    .loginId(aliasName + sequence)
                    .password(passwordEncoder.encode("1234"))
                    .name("name_" + aliasName + sequence)
                    .email(aliasName + sequence + "@module.com")
                    .age(sequence)
                    .build();
            return member;
        }
    }
}
