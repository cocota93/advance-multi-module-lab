package org.jedy.document;

import org.jedy.member.domain.MemberAuthType;
import org.jedy.system_core.global.error.exception.ErrorCode;
import org.jedy.system_core.support.EnumType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class EnumViewController {


    @GetMapping("/docs")
    public Docs findAll() {

        Map<String, String> apiResponseCodes = getDocs(ErrorCode.values());
        Map<String, String> memberAuth = getDocs(MemberAuthType.values());


        Docs build = Docs.testBuilder()
                         .apiResponseCodes(apiResponseCodes)
                         .memberAuth(memberAuth)
                         .build();
        return build;
    }

    private Map<String, String> getDocs(EnumType[] enumTypes) {
        return Arrays.stream(enumTypes)
                .collect(Collectors.toMap(EnumType::getCode, EnumType::getMessage));
    }
}
