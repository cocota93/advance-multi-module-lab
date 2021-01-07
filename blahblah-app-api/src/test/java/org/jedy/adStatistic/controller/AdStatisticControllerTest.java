package org.jedy.adStatistic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jedy.RestDocConfiguration;
import org.jedy.ad_statistic_core.domain.AdHourlyStatistic;
import org.jedy.ad_statistic_core.dto.req.AdStatisticSearchCondition;
import org.jedy.ad_statistic_core.dto.req.ReqUploadAdStatistic;
import org.jedy.ad_statistic_core.dto.res.ResAdHourlyStatistic;
import org.jedy.ad_statistic_core.dto.res.ResUploadAdHourlyStatistic;
import org.jedy.ad_statistic_core.repository.AdHourlyStatisticRepository;
import org.jedy.ad_statistic_core.service.AdHourlyStatisticService;
import org.jedy.security.JwtTokenProvider;
import org.jedy.security.WebSecurityConfig;
import org.jedy.system_core.global.response.ResponseService;
import org.jedy.system_core.global.response.SingleResult;
import org.jedy.system_core.util.ConverterUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.Arrays;

import static org.jedy.ad_statistic_core.domain.QAdHourlyStatistic.adHourlyStatistic;
import static org.jedy.config.DocumentFormatGenerator.getDateFormat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(controllers = AdStatisticController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class, WebSecurityConfig.class})
//@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AdStatisticController.class)
@AutoConfigureRestDocs
//@Import({RestDocConfiguration.class, WebSecurityConfig.class, JwtTokenProvider.class}) // 테스트 설정 import
@Import({RestDocConfiguration.class}) // 테스트 설정 import
class AdStatisticControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private AdHourlyStatisticService adHourlyStatisticService;
    @MockBean private JwtTokenProvider jwtTokenProvider;
    @MockBean private ResponseService responseService;
//    @MockBean private AdHourlyStatisticRepository adHourlyStatisticRepository;

//    @Autowired private JwtTokenProvider jwtTokenProvider;

    private AdHourlyStatistic adHourlyStatistic;

    @BeforeEach
    public void setup() {
        System.out.println("gigigi");
        adHourlyStatistic = AdHourlyStatistic.builder()
                .id(1L)
                .targetDate(LocalDate.of(2020, 11, 29))
                .hour(8)
                .reqCount(13)
                .resCount(11)
                .clickCount(10)
                .build();


        ResUploadAdHourlyStatistic resUploadAdHourlyStatistic = new ResUploadAdHourlyStatistic();
        resUploadAdHourlyStatistic.setId(1L);
        resUploadAdHourlyStatistic.setTargetDate(LocalDate.now());
        resUploadAdHourlyStatistic.setHour(3);
        resUploadAdHourlyStatistic.setReqCount(4);
        resUploadAdHourlyStatistic.setResCount(3);
        resUploadAdHourlyStatistic.setClickCount(4);

    }

    @Test
    public void aa() throws Exception{
        //given

        //when

        //then
    }

    @Test
    void search() throws Exception {
        //given
        given(adHourlyStatisticService.search(new AdStatisticSearchCondition()))
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
                                fieldWithPath("targetDate").type(JsonFieldType.STRING).description("날짜").optional(),
                                fieldWithPath("hour").type(JsonFieldType.NUMBER).description("시간").optional()
                        )
//                        responseFields(beneathPath("data").withSubsectionId("data"),
//                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디"),
//                                        fieldWithPath("targetDate").type(JsonFieldType.STRING).description("날짜"),
//                                        fieldWithPath("hour").type(JsonFieldType.NUMBER).description("시간"),
//                                        fieldWithPath("reqCount").type(JsonFieldType.NUMBER).description("광고 요청횟수"),
//                                        fieldWithPath("resCount").type(JsonFieldType.NUMBER).description("광고 응답횟수"),
//                                        fieldWithPath("clickCount").type(JsonFieldType.NUMBER).description("광고 클릭횟수")
//                                )
                )
                )
        ;
    }


    @Test
    void doUpload() throws Exception {
        //given
        ResUploadAdHourlyStatistic resUploadAdHourlyStatistic = new ResUploadAdHourlyStatistic();
        resUploadAdHourlyStatistic.setId(1L);
        resUploadAdHourlyStatistic.setTargetDate(LocalDate.now());
        resUploadAdHourlyStatistic.setHour(3);
        resUploadAdHourlyStatistic.setReqCount(4);
        resUploadAdHourlyStatistic.setResCount(3);
        resUploadAdHourlyStatistic.setClickCount(4);

        SingleResult<Object> tempResult = new SingleResult<>();
        tempResult.setData(resUploadAdHourlyStatistic);
        tempResult.setStatus(200);
        tempResult.setMessage("dd");

        given(responseService.getSingleResult(any()))
                .willReturn(tempResult)
        ;

        //!TODO request객체 내에 targetDate, hour값 null 넣어도 된다고 표현하고픈데 잘 안됨
        ReqUploadAdStatistic request = new ReqUploadAdStatistic();
        request.setTargetDate(LocalDate.of(2020, 11, 29));
        request.setHour(8);
        request.setReqCount(13);
        request.setResCount(11);
        request.setClickCount(10);

        //when
        ResultActions resultAction = this.mockMvc.perform(
                get("/api/adStatistic/upload")
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
                                fieldWithPath("targetDate").type(JsonFieldType.STRING).description("날짜"),
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
                                fieldWithPath("clickCount").type(JsonFieldType.NUMBER).description("광고 클릭횟수")
                        )
                )
        )
        ;
    }

}