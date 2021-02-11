package org.jedy.adStatistic.controller;

import org.jedy.ApiDocumentationTest;
import org.jedy.ad_statistic_core.domain.AdHourlyStatistic;
import org.jedy.ad_statistic_core.dto.req.AdStatisticSearchCondition;
import org.jedy.ad_statistic_core.dto.req.ReqUploadAdStatistic;
import org.jedy.ad_statistic_core.dto.res.ResAdHourlyStatistic;
import org.jedy.ad_statistic_core.service.AdHourlyStatisticService;
import org.jedy.system_core.util.ConverterUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.Arrays;

import static org.jedy.document.utils.DocumentFormatGenerator.getDateFormat;
import static org.jedy.document.utils.DocumentLinkGenerator.DocUrl.SOMETHINGENUM;
import static org.jedy.document.utils.DocumentLinkGenerator.generateLinkCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = {
        AdStatisticController.class
})
class AdStatisticControllerTest extends ApiDocumentationTest {

    @MockBean private AdHourlyStatisticService adHourlyStatisticService;

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