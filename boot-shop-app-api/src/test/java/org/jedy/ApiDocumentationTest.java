package org.jedy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jedy.document.EnumViewController;
import org.jedy.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;


//@Import(ResponseService.class)
@WebMvcTest(controllers = {
        EnumViewController.class,
})
@AutoConfigureRestDocs
public abstract class ApiDocumentationTest {

    @Autowired
    protected MockMvc mockMvc;

    @SpyBean
    protected ObjectMapper objectMapper;


    @SpyBean
    protected  JwtTokenProvider jwtTokenProvider;


}

//Service 주입 : https://spring.io/guides/gs/multi-module/
//스프링(Spring)에서 Error creating bean with name 에러 발생시 : https://needneo.tistory.com/10
