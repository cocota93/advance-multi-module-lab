package org.jedy.category.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.category.domain.Category;
import org.jedy.category.dto.request.CategoryRequestDto;
import org.jedy.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Long addCategory(CategoryRequestDto categoryRequestDto) {
        Category save = categoryRepository.save(
                Category.builder()
                        .catCd(categoryRequestDto.getCatCd())
                        .catName(categoryRequestDto.getCatName())
                        .upperCatCd(categoryRequestDto.getUpperCatCd())
//                        .catLv(categoryRequestDto.getCatLv())
                        .use(true)
                        .build()
        );

        return save.getId();
    }
}

