package org.jedy.operator.controller;

import org.jedy.operator.dto.OperatorCreateResponse;
import org.jedy.operator_core.domain.Operator;
import org.jedy.operator_core.domain.OperatorAuth;
import org.jedy.operator_core.domain.OperatorAuthType;
import org.jedy.operator_core.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/operator")
public class OperatorController {

    @Autowired OperatorRepository operatorRepository;

    @Autowired PasswordEncoder passwordEncoder;

    @GetMapping("/create")
    @ResponseBody
    public Object create(){
//        Operator operator = new Operator("jedy", "1234");
        Operator operator = new Operator("jedy", passwordEncoder.encode("1234"));
        operator.addAuthority(new OperatorAuth(operator, OperatorAuthType.PAY_MANAGER));
        operatorRepository.save(operator);
        return new OperatorCreateResponse(operator);
    }

}
