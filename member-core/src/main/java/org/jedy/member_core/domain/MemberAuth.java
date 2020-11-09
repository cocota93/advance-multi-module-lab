package org.jedy.member_core.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jedy.system_core.entity.BaseTimeEntity;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={"owner_id", "type"}
                )
        }
)
@ToString(of = {"id", "type"} )
public class MemberAuth extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_auth_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private MemberAuthType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Member owner;

    public MemberAuth(Member owner, MemberAuthType type) {
        this.owner = owner;
        this.type = type;
    }

}
