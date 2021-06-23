package org.jedy.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.jedy.system_core.entity.BaseTimeEntity;
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
public class Member extends BaseTimeEntity {

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

  private Integer age;

  @JsonIgnore
  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
  private List<MemberAuth> authorityList = new ArrayList<>();


  @Builder(builderClassName = "BySignup", builderMethodName = "BySignup")
  public Member(String loginId, String password, String name, String email, Integer age) {
    this.loginId = loginId;
    this.password = password;
    this.name = name;
    this.email = email;
    this.age = age;
  }

  public void addAuthority(/*@NotNull*/ MemberAuth memberAuth) {
    if(memberAuth == null || this != memberAuth.getOwner()){
      throw new BusinessException(ErrorCode.ADD_AUTH_OWNER_NOT_EQUAL);
    }

    authorityList.add(memberAuth);
  }

  public boolean EqualLoginId(String loginId) {
    return this.loginId.equals(loginId);
  }
}
