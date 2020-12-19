package org.jedy.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.member.dto.response.MemberCreateResponse;
import org.jedy.member_core.domain.ReqSignupMember;
import org.jedy.member.service.MemberAuthService;
import org.jedy.system_core.global.response.ResponseService;
import org.jedy.system_core.global.response.SingleResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {

    private final MemberAuthService memberAuthService;
    private final ResponseService responseService;


    @GetMapping(value = "/create")
    public SingleResult<MemberCreateResponse> create() {
        return responseService.getSingleResult(memberAuthService.testCreate());
    }


    @PostMapping(value = "/signup")
    public SingleResult<MemberCreateResponse> signup(@Valid ReqSignupMember reqSignupMember) {
        MemberCreateResponse member = memberAuthService.signup(reqSignupMember);
        return responseService.getSingleResult(member);
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam String loginId, @RequestParam String password) {
        String loginToken = memberAuthService.login(loginId, password);
        return loginToken;
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
