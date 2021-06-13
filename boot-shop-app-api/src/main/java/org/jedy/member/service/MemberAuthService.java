package org.jedy.member.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.member.domain.Member;
import org.jedy.member.domain.MemberAuth;
import org.jedy.member.domain.MemberAuthType;
import org.jedy.member.domain.ReqSignupMember;
import org.jedy.member.exception.MemberSignupDuplicationException;
import org.jedy.member.repository.MemberRepository;
import org.jedy.system_core.global.error.exception.EntityNotFoundException;
import org.jedy.member.dto.response.MemberCreateResponse;
import org.jedy.member.exception.MemberLoginFailException;
import org.jedy.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberAuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    @GetMapping(value = "/create")
    public MemberCreateResponse testCreate() {
        String encode = passwordEncoder.encode("1234");
        log.info("createEncode : " + encode);
        Member member = new Member("jedy", encode, "ryong", "cocota93@gmail.com", 28);
        member.addAuthority(new MemberAuth(member, MemberAuthType.COMMON_USER));
        memberRepository.save(member);
        return new MemberCreateResponse(member);

    }

    public MemberCreateResponse signup(@Valid ReqSignupMember reqSignupMember) {
        Long memberId = defaultSignupMember(reqSignupMember);
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("signup is not working"));
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
        member.addAuthority(new MemberAuth(member, MemberAuthType.COMMON_USER));
        return memberRepository.save(member).getId();
    }


    public String login(String loginId, String password) {
        Member loginMember = memberRepository.findFetchAuthByLoginId(loginId)
                .orElseThrow(() -> new EntityNotFoundException(loginId));

        if(!passwordEncoder.matches(password, loginMember.getPassword())){
            throw new MemberLoginFailException(loginId);
        }

        List<String> authorityList = loginMember.getAuthorityList().stream()
                .map(auth -> auth.getType().getAuthority())
                .collect(Collectors.toList());

        String token = jwtTokenProvider.createToken(String.valueOf(loginMember.getLoginId()), authorityList);
        return token;
    }


}
