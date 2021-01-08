package org.jedy.adStatistic.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jedy.RestDocConfiguration;
import org.jedy.ad_statistic_core.domain.AdHourlyStatistic;
import org.jedy.ad_statistic_core.dto.req.AdStatisticSearchCondition;
import org.jedy.ad_statistic_core.dto.req.ReqUploadAdStatistic;
import org.jedy.ad_statistic_core.dto.res.ResAdHourlyStatistic;
import org.jedy.ad_statistic_core.dto.res.ResUploadAdHourlyStatistic;
import org.jedy.ad_statistic_core.service.AdHourlyStatisticService;
import org.jedy.config.CustomResponseFieldsSnippet;
import org.jedy.document.Docs;
import org.jedy.document.EnumViewController;
import org.jedy.member.service.MemberAuthService;
import org.jedy.member_core.domain.Member;
import org.jedy.member_core.domain.MemberAuth;
import org.jedy.member_core.domain.MemberAuthType;
import org.jedy.member_core.repository.MemberRepository;
import org.jedy.security.JwtAuthenticationFilter;
import org.jedy.security.JwtTokenProvider;
import org.jedy.system_core.global.response.ResponseService;
import org.jedy.system_core.global.response.SingleResult;
import org.jedy.system_core.util.ConverterUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.jedy.config.DocumentFormatGenerator.getDateFormat;
import static org.jedy.config.DocumentLinkGenerator.DocUrl.SOMETHINGENUM;
import static org.jedy.config.DocumentLinkGenerator.generateLinkCode;
import static org.jedy.config.DocumentLinkGenerator.generateText;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;



//@WebMvcTest(controllers = {AdStatisticController.class, EnumViewController.class}, excludeFilters = {@ComponentScan.Filter(classes = JwtAuthenticationFilter.class)})
//@WebMvcTest(controllers = {AdStatisticController.class, EnumViewController.class}, excludeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = {JwtAuthenticationFilter.class, })})
@WebMvcTest(controllers = {AdStatisticController.class, EnumViewController.class})
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureRestDocs
@Import({RestDocConfiguration.class}) // 테스트 설정 import
class AdStatisticControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private AdHourlyStatisticService adHourlyStatisticService;
    @SpyBean private JwtTokenProvider jwtTokenProvider;
    @SpyBean private ResponseService responseService;

    @SpyBean protected ObjectMapper objectMapper;

    private AdHourlyStatistic adHourlyStatistic;

    @BeforeEach
    public void setup() {
        adHourlyStatistic = AdHourlyStatistic.builder()
                .id(1L)
                .targetDate(LocalDate.of(2020, 11, 29))
                .hour(8)
                .reqCount(13)
                .resCount(11)
                .clickCount(10)
                .build();

    }


    @Test
    void search() throws Exception {
        //given
        given(adHourlyStatisticService.search(any()))
                .willReturn(
                        Arrays.asList(new ResAdHourlyStatistic(adHourlyStatistic))
                );

        AdStatisticSearchCondition request = new AdStatisticSearchCondition();
        request.setTargetDate(LocalDate.of(2020, 11, 29));
        request.setHour(8);

        //when
        ResultActions resultAction = this.mockMvc.perform(
                get("/api/adStatistic/search")
                        .content(ConverterUtil.convertObjectToJson(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        resultAction.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("ad_hourly_statistic_search",
                        requestFields(
                                fieldWithPath("targetDate").type(JsonFieldType.STRING).attributes(getDateFormat()).description("날짜").optional(),
                                fieldWithPath("hour").type(JsonFieldType.NUMBER).description("시간").optional()
                        ),
                        responseFields(beneathPath("data").withSubsectionId("data"),
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디"),
                                        fieldWithPath("targetDate").type(JsonFieldType.STRING).attributes(getDateFormat()).description("날짜"),
                                        fieldWithPath("hour").type(JsonFieldType.NUMBER).description("시간"),
                                        fieldWithPath("reqCount").type(JsonFieldType.NUMBER).description("광고 요청횟수"),
                                        fieldWithPath("resCount").type(JsonFieldType.NUMBER).description("광고 응답횟수"),
                                        fieldWithPath("clickCount").type(JsonFieldType.NUMBER).description("광고 클릭횟수")
                                )
                )
                )
        ;
    }


    @Test
    void doUpload() throws Exception {
        //given
        given(adHourlyStatisticService.findById(any()))
                .willReturn(AdHourlyStatistic.builder()
                .id(1L)
                .targetDate(LocalDate.now())
                .hour(8)
                .clickCount(10)
                .reqCount(10)
                .resCount(8)
                .build());

        ReqUploadAdStatistic request = new ReqUploadAdStatistic();
        request.setTargetDate(LocalDate.of(2020, 11, 29));
        request.setHour(8);
        request.setReqCount(13);
        request.setResCount(11);
        request.setClickCount(10);

        //when
        ResultActions resultAction = this.mockMvc.perform(
                post("/api/adStatistic/upload")
//                        .header("X-AUTH-TOKEN", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZWR5Iiwicm9sZXMiOlsiQ09NTU9OX1VTRVIiXSwiaWF0IjoxNjEwMTA2MTUyLCJleHAiOjE2MTAxMDk3NTJ9.8swYLv5PMh32j2pDdQ94ZfP1kNLFZ0v9OCnwJadSFkQ")
//                        .with(csrf())
                        .content(ConverterUtil.convertObjectToJson(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        resultAction
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("ad_hourly_statistic_upload",
                        requestFields(
                                fieldWithPath("targetDate").type(JsonFieldType.STRING).attributes(getDateFormat()).description("날짜"),
                                fieldWithPath("hour").type(JsonFieldType.NUMBER).description("시간"),
                                fieldWithPath("reqCount").type(JsonFieldType.NUMBER).description("광고 요청횟수"),
                                fieldWithPath("resCount").type(JsonFieldType.NUMBER).description("광고 응답횟수"),
                                fieldWithPath("clickCount").type(JsonFieldType.NUMBER).description("광고 클릭횟수")
                        ),
                        responseFields(beneathPath("data").withSubsectionId("data"),
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디"),
                                fieldWithPath("targetDate").type(JsonFieldType.STRING).attributes(getDateFormat()).description("날짜"),
                                fieldWithPath("hour").type(JsonFieldType.NUMBER).description("시간"),
                                fieldWithPath("reqCount").type(JsonFieldType.NUMBER).description("광고 요청횟수"),
                                fieldWithPath("resCount").type(JsonFieldType.NUMBER).description("광고 응답횟수"),
//                                fieldWithPath("clickCount").type(JsonFieldType.NUMBER).description("광고 클릭횟수")
                                fieldWithPath("clickCount").type(JsonFieldType.NUMBER).description(generateLinkCode(SOMETHINGENUM))
                        )
                        )
                )
        ;
    }

}

// webmvctest를 이용하여 테스트할때 security filter를 제거하는 방법 : https://stackoverflow.com/questions/47593537/disable-spring-security-config-class-for-webmvctest-in-spring-boot