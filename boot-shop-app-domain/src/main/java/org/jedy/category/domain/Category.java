package org.jedy.category.domain;

import lombok.*;

import javax.persistence.*;

/*
* 카테고리에 왜 별도의 코드라는 변수를 두고 관리하는지 의문이었음.
* -> 하나의 카테고리에 여러 하위 카테고리가 올수 있어야 하니까
*
* 카테고리 설계
* https://www.inflearn.com/questions/36695
* */

/*
* 상위 카테고리랑 카테고리 레벨이 같으면 같은 카테고리 군이네??
* */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column
    private String catCd;

    @Column(length = 50)
    private String catName;

    @Column(length = 10)
    private String upperCatCd;

//    @Column(length = 11)
//    private Integer catLv;

    @Column
    private boolean useSlot;

}
