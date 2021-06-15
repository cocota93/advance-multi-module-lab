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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberAuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;




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
