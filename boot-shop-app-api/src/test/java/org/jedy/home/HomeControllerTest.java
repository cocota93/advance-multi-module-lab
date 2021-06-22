package org.jedy.home;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeControllerTest {


//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void Profile확인 () {
//        //when
//        String profile = this.restTemplate.getForObject("/profile", String.class);
//
//        //then
//        assertEquals("local", profile);
//    }
}