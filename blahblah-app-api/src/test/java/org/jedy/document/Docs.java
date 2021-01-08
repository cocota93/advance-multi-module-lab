package org.jedy.document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class Docs {

    Map<String, String> apiResponseCodes;
    Map<String, String> memberAuth;

    @Builder(builderClassName = "TestBuilder", builderMethodName = "testBuilder")
    private Docs(Map<String, String> apiResponseCodes, Map<String, String> memberAuth) {
        this.apiResponseCodes = apiResponseCodes;
        this.memberAuth = memberAuth;
    }
}
