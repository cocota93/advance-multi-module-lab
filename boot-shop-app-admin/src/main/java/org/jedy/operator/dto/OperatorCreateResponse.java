package org.jedy.operator.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jedy.operator.domain.Operator;
import org.jedy.operator.domain.OperatorAuth;
import org.jedy.operator.domain.OperatorAuthType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OperatorCreateResponse {

    private Long id;

    private String loginId;

    private List<OperatorAuthType> authList = new ArrayList<>();

    public OperatorCreateResponse(Operator operator) {
        this.id = operator.getId();
        this.loginId = operator.getLoginId();
        authList = operator.getAuthorityList().stream()
                .map(OperatorAuth::getType)
                .collect(Collectors.toList());
    }
}
