//package org.jedy.member.controller;
//
//import org.jedy.member.dto.response.MemberCreateResponse;
//import org.jedy.member.service.MemberAuthService;
//import org.jedy.member_core.domain.Member;
//import org.jedy.security.JwtTokenProvider;
//import org.jedy.system_core.global.response.ResponseService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.test.util.AssertionErrors.assertEquals;
//import static org.springframework.test.util.AssertionErrors.assertNotNull;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@WebMvcTest(MemberApiController.class)
//@AutoConfigureRestDocs
//class MemberApiControllerTest {
//
//
////    @Autowired private PasswordEncoder passwordEncoder;
//
//    @Autowired private MockMvc mockMvc;
//    @SpyBean private MemberAuthService memberAuthService;
//    @SpyBean private ResponseService responseService;
//    @SpyBean private  PasswordEncoder passwordEncoder;
//    @SpyBean private  JwtTokenProvider jwtTokenProvider;
////    @SpyBean private  auditorProvider jwtTokenProvider;
////    @SpyBean private  JpaAudi jwtTokenProvider;
//
//    @Test
//    void restdoctest() throws Exception {
//        //given
//        Member build = Member.BySignup()
//                .loginId("jedy")
//                .password(passwordEncoder.encode("1234"))
//                .name("ryong")
//                .email("cocota93@gmail.com")
//                .age(28)
//                .build();
//
//        given(memberAuthService.testCreate())
//                .willReturn(
//                        new MemberCreateResponse(build)
//                );
//
//        //when
//        ResultActions resultAction = this.mockMvc.perform(
//                get("/api/members")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//        );
//
//        //then
//        resultAction.andExpect(status().isOk())
//                .andDo(print())
//                .andDo(document("restdoctest",
//                        responseFields(beneathPath("data").withSubsectionId("data"),
//                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디"),
//                                fieldWithPath("loginId").type(JsonFieldType.STRING).description("로그인_아이디"),
//                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
//                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일")
//                        )
//                ));
//
//    }
//}