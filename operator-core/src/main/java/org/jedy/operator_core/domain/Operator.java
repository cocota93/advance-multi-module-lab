package org.jedy.operator_core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jedy.system_core.entity.BaseTimeEntity;
import org.jedy.system_core.global.error.exception.BusinessException;
import org.jedy.system_core.global.error.exception.ErrorCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints={
            @UniqueConstraint(
                    columnNames={"loginId"}
            )
        }
)
@ToString(of = {"id", "loginId"} )
public class Operator extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operator_id")
    private Long id;

    @Column(nullable = false)
    private String loginId;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<OperatorAuth> authorityList = new ArrayList<>();

    public Operator(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public void addAuthority(/*@NotNull*/ OperatorAuth operatorAuth){
        if(operatorAuth == null || this != operatorAuth.getOwner()){
            //TODO 별도로 예외를 만들어서 발생시킬려 했더니 의존성 역전 발생. 어떻게 해야될까?
            throw new BusinessException(ErrorCode.ADD_AUTH_OWNER_NOT_EQUAL);
        }

        authorityList.add(operatorAuth);
    }
}
