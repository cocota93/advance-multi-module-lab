package org.jedy.document;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.jedy.ApiDocumentationTest;
import org.jedy.document.utils.CustomResponseFieldsSnippet;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
public class CommonDocumentationTests extends ApiDocumentationTest {
//
@Test
public void commons() throws Exception{
    ResultActions result = this.mockMvc.perform(
            get("/docs")
                    .accept(MediaType.APPLICATION_JSON)
    );

    MvcResult mvcResult = result.andReturn();
    Docs docs = getData(mvcResult);

    //then
//        result.andExpect(status().isOk())
//                .andDo(document("common",
//                        customResponseFields("custom-response", null,
//                                attributes(key("title").value("공통응답")),
//                                subsectionWithPath("data").description("데이터"),
//                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("결과코드"),
//                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과메시지")
//                        ),
//                        customResponseFields("custom-response", beneathPath("data.apiResponseCodes").withSubsectionId("apiResponseCodes"),
//                                attributes(key("title").value("응답코드")),
//                                enumConvertFieldDescriptor(docs.getApiResponseCodes())
//                        )
//                ));
}

    private static FieldDescriptor[] enumConvertFieldDescriptor(Map<String, String> enumValues) {

        return enumValues.entrySet().stream()
                         .map(x -> fieldWithPath(x.getKey()).description(x.getValue()))
                         .toArray(FieldDescriptor[]::new);
    }

    Docs getData(MvcResult result) throws IOException {
        Docs apiResponseDto = objectMapper.readValue(result.getResponse().getContentAsByteArray(),
                new TypeReference<Docs>() {
                });

        return apiResponseDto;
    }

    public static CustomResponseFieldsSnippet customResponseFields(String type,
                                                                   PayloadSubsectionExtractor<?> subsectionExtractor,
                                                                   Map<String, Object> attributes, FieldDescriptor... descriptors) {
        return new CustomResponseFieldsSnippet(type, subsectionExtractor, Arrays.asList(descriptors), attributes
                , true);
    }
}
