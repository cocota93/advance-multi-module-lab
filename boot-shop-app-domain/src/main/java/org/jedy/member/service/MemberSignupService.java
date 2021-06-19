package org.jedy.member.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.cart.domain.Cart;
import org.jedy.cart.repository.CartRepository;
import org.jedy.member.domain.Member;
import org.jedy.member.domain.MemberAuth;
import org.jedy.member.domain.MemberAuthType;
import org.jedy.member.domain.ReqSignupMember;
import org.jedy.member.dto.response.MemberCreateResponse;
import org.jedy.member.exception.MemberSignupDuplicationException;
import org.jedy.member.repository.MemberAuthRepository;
import org.jedy.member.repository.MemberRepository;
import org.jedy.system_core.global.error.exception.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class MemberSignupService {
    private final MemberRepository memberRepository;
    private final MemberAuthRepository MemberAuthRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;


    public MemberCreateResponse signup(@Valid ReqSignupMember reqSignupMember) {
        Long memberId = defaultSignupMember(reqSignupMember);
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("signup is not working"));

        for (int i = 0; i < 20; i++) {
            cartRepository.save(Cart.InitAssignBySignup().member(member).build());
        }

        return new MemberCreateResponse(member);
    }

    //cascade가 원자적으로 동작하나? member랑 memberAuth랑 각각 한번씩 저장해야되는데 이게 원자적으로 되는건가?
    private Long defaultSignupMember(ReqSignupMember reqSignupMember) {
        memberRepository.findByLoginId(reqSignupMember.getLoginId()).ifPresent(oldMember -> {
            throw new MemberSignupDuplicationException(oldMember.getLoginId());
        });

        Member member = Member.BySignup()
                              .loginId(reqSignupMember.getLoginId())
                              .password(passwordEncoder.encode(reqSignupMember.getPassword()))
                              .name(reqSignupMember.getName())
                              .email(reqSignupMember.getEmail())
                              .age(reqSignupMember.getAge())
                              .build();
        memberRepository.save(member);

        MemberAuth memberAuth = new MemberAuth(member, MemberAuthType.COMMON_USER);
        MemberAuthRepository.save(memberAuth);

        member.addAuthority(memberAuth);
        return member.getId();
    }

}

