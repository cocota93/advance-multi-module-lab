package org.jedy.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.member.dto.response.MemberCreateResponse;
import org.jedy.member.domain.Member;
import org.jedy.member.domain.ReqLoginMember;
import org.jedy.member.domain.ReqSignupMember;
import org.jedy.member.service.MemberAuthService;
import org.jedy.member.repository.MemberRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {

    private final MemberAuthService memberAuthService;
    private final MemberRepository memberRepository;


    @GetMapping(value = "/create")
    public MemberCreateResponse create() {
        return memberAuthService.testCreate();
    }


    @PostMapping(value = "/signup")
    public MemberCreateResponse signup(@Valid @RequestBody ReqSignupMember reqSignupMember) {
        MemberCreateResponse member = memberAuthService.signup(reqSignupMember);
        return member;
    }

    @PostMapping(value = "/login")
    public String login(@Valid @RequestBody ReqLoginMember reqLoginMember) {
        String loginToken = memberAuthService.login(reqLoginMember.getLoginId(), reqLoginMember.getPassword());
        return loginToken;
    }

    //postman으로 헤더에 X_AUTH_TOKEN 제대로 안넣어서 보내면 requestId가 익명으로 나오고
    //제대로 넣어서 보내면 누군지 식별됨.
    //이대한 일련의 과정은 JwtAuthenticationFilter에서 처리됨.
    @PostMapping(value = "/jwtcheck")
    public String jwtcheck(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String requestId = authentication.getName();

        Member findMember = memberRepository.findByLoginId(requestId).orElseGet(null);
        if(findMember == null){
            return "";
        }else {
            log.info("findMember : " +findMember.getLoginId());
        }

        return "jwtcheck_success";
    }

    @PostMapping(value = "/findLoginId")
    public void findLoginId() {
        memberAuthService.findLoginId();
    }

    @PostMapping(value = "/findPassword")
    public void findPassword() {
        memberAuthService.findPassword();
    }

    @PostMapping(value = "/modifyPassword")
    public void modifyPassword() {
        memberAuthService.modifyPassword();
    }


//    이메일인증
}
