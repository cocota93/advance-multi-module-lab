package org.jedy.member_core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jedy.system_core.global.error.exception.BusinessException;
import org.jedy.system_core.global.error.exception.ErrorCode;

import javax.persistence.*;
//import javax.validation.constraints.NotNull;
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
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;

  @Column(nullable = false, updatable = false)
  private String loginId;

  @Column(nullable = false)
  private String password;

  private String name;

  private String email;

  @JsonIgnore
  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
  private List<MemberAuth> authorityList = new ArrayList<>();


  @Builder(builderClassName = "BySignup", builderMethodName = "BySignup")
  public Member(String loginId, String password, String name, String email) {
    this.loginId = loginId;
    this.password = password;
    this.name = name;
    this.email = email;
  }

  public void addAuthority(/*@NotNull*/ MemberAuth memberAuth) {
    if(memberAuth == null || this != memberAuth.getOwner()){
      //TODO 별도로 예외를 만들어서 발생시킬려 했더니 의존성 역전 발생. 어떻게 해야될까?
//            throw new MemberAuthAddException();
      throw new BusinessException(ErrorCode.ADD_AUTH_OWNER_NOT_EQUAL);
    }

    authorityList.add(memberAuth);
  }
}
