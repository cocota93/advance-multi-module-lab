package org.jedy;

import lombok.RequiredArgsConstructor;
import org.jedy.category.dto.request.CategoryRequestDto;
import org.jedy.category.repository.CategoryRepository;
import org.jedy.category.service.CategoryService;
import org.jedy.member.domain.Member;
import org.jedy.member.repository.MemberRepository;
import org.jedy.product.dto.request.ProductRequestDto;
import org.jedy.product.service.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Profile("initdb")
public class initDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.categoryInit();
        initService.productListInit();
//        initService.memberInit();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final PasswordEncoder passwordEncoder;
        private final MemberRepository memberRepository;
        private final CategoryRepository categoryRepository;

        private final ProductService productService;
        private final CategoryService categoryService;

        public void categoryInit(){
            {
                CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
                categoryRequestDto.setCatName("전자제품");
                categoryRequestDto.setCatCd("cd000001");
                categoryService.addCategory(categoryRequestDto);
            }
            {
                CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
                categoryRequestDto.setCatName("휴대폰");
                categoryRequestDto.setCatCd("cd000002");
                categoryRequestDto.setUpperCatCd("cd000001");
                categoryService.addCategory(categoryRequestDto);
            }

            {
                CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
                categoryRequestDto.setCatName("의류");
                categoryRequestDto.setCatCd("cd000003");
                categoryService.addCategory(categoryRequestDto);
            }
        }
        public void productListInit() {
            createProduct("갤럭시s7", 10000L, "cd000002");
            createProduct("세탁기", 3000L, "cd000001");
            createProduct("G-PRO2", 50000L, "cd000002");
            createProduct("물빠진 청바지", 90000L, "cd000003");
            createProduct("찢어진 청바지", 10000L, "cd000003");
            createProduct("껌정 맨투맨", 30000L, "cd000003");
        }
        public void memberInit() {
            for(int i = 1; i < 3; ++i){
                Member member = createMember("member", i);
                memberRepository.save(member);
            }
        }

        private void createProduct(String name, long price, String upperCatCd) {
            ProductRequestDto productRequestDto = new ProductRequestDto();
            productRequestDto.setName(name);
            productRequestDto.setPrice(price);
            productRequestDto.setUpperCatCd(upperCatCd);
            productService.addProduct(productRequestDto);
        }
        private Member createMember(String aliasName, Integer sequence) {
            Member member = Member.BySignup()
                    .loginId(aliasName + sequence)
                    .password(passwordEncoder.encode("1234"))
                    .name("name_" + aliasName + sequence)
                    .email(aliasName + sequence + "@module.com")
                    .age(sequence)
                    .build();
            return member;
        }
//        private Product createProduct(){
////            productService.addProduct()
//        }

    }
}
