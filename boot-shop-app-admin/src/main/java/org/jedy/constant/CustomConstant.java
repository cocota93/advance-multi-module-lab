package org.jedy.constant;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CustomConstant {

    @Value("${spring.testdata.operator.id}")
    private String testOperatorId;

    @Value("${spring.testdata.operator.password}")
    private String testOperatorPassword;
}
