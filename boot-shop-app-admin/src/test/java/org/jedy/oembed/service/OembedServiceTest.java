//package org.jedy.oembed.service;
//
//import org.jedy.oembed.repository.query.OembedQueryRepository;
//import org.jedy.oembed_core.util.OembedUrlUtil;
//import org.jedy.oembed_core.domain.Oembed;
//import org.jedy.oembed_core.service.OembedServiceImpl;
//import org.jedy.system_core.util.HttpUtil;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.net.URL;
//
//@SpringBootTest
//@Transactional
//class OembedServiceTest {
//
//    @Autowired OembedServiceImpl oembedService;
//    @Autowired OembedQueryRepository oembedQueryRepository;
//
//    @Test
//    public void youtube1() throws Exception{
//        //given
//
//        //when
//        String testUrl = "youtu.be/Chgq1SWxa5Q";
//
//        //공지사항 만들때는 게시물의 string데이터를 파라미터로 넘기면 notice를 만들어서 저장후 해당id를 반환했다.
//        //여기서도 response를 파라미터로 넘기면 저장후 id를 반환해서 통일성을 갖도록 해야할까?
//        //그런것까지 통일시키는건 너무 과한걸까? 나중에 bulk insert하는 상황을 생각해서 저장부분은 분리해놔야하나?
//        URL apiUrl = OembedUrlUtil.createApiUrl(testUrl);
//        StringBuilder response = HttpUtil.get(apiUrl);
//        Long createId = oembedService.create(response.toString());
//        Oembed oembed = oembedService.findById(createId);
//        //then
//        System.out.println(response.toString());
//    }
//
//    @Test
//    public void youtube2() throws Exception{
//        //given
//
//        //when
//        String testUrl = "https://youtu.be/Chgq1SWxa5Q";
//
//        URL apiUrl = OembedUrlUtil.createApiUrl(testUrl);
//        StringBuilder response = HttpUtil.get(apiUrl);
//        Long createId = oembedService.create(response.toString());
//        Oembed oembed = oembedService.findById(createId);
//        //then
//        System.out.println(response.toString());
//    }
//
//    @Test
//    public void youtube3() throws Exception{
//        //given
//
//        //when
//        String testUrl = "https://youtu.be/Chgq1SWxa5Q?list=RDMMChgq1SWxa5Q";
//
//        URL apiUrl = OembedUrlUtil.createApiUrl(testUrl);
//        StringBuilder response = HttpUtil.get(apiUrl);
//        Long createId = oembedService.create(response.toString());
//        Oembed oembed = oembedService.findById(createId);
//        //then
//        System.out.println(response.toString());
//    }
////
//////    @Test
//////    public void insta() throws Exception{
//////        //given
//////
////
////        //액세스 토큰이 있어야 할수있는데 그럴려면 앱 등록이 필요함
//////        //when
//////        String testUrl = "https://www.instagram.com/p/CI2b5cupZJn";
//////    }
////
//
//    @Test
//    public void twitter1() throws Exception{
//        //given
//
//        //when
//        String testUrl = "https://twitter.com/hellopolicy/status/867177144815804416";
//        URL apiUrl = OembedUrlUtil.createApiUrl(testUrl);
//        StringBuilder response = HttpUtil.get(apiUrl);
//        Long createId = oembedService.create(response.toString());
//        Oembed oembed = oembedService.findById(createId);
//        //then
//        System.out.println(response.toString());
//    }
//
//
//    @Test
//    public void vimeo1() throws Exception{
//        //given
//
//        //when
//        String testUrl = "https://vimeo.com/20097015";
//        URL apiUrl = OembedUrlUtil.createApiUrl(testUrl);
//        StringBuilder response = HttpUtil.get(apiUrl);
//        Long createId = oembedService.create(response.toString());
//        Oembed oembed = oembedService.findById(createId);
//        //then
//        System.out.println(response.toString());
//    }
//
//
//
//
//}