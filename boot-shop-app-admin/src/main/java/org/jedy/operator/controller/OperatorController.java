package org.jedy.operator.controller;

import org.jedy.operator.domain.Operator;
import org.jedy.operator.domain.OperatorAuth;
import org.jedy.operator.domain.OperatorAuthType;
import org.jedy.operator.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String create(Model model){
        Operator operator = new Operator("jedy", passwordEncoder.encode("1234"));
        operator.addAuthority(new OperatorAuth(operator, OperatorAuthType.PAY_MANAGER));
        operatorRepository.save(operator);

        model.addAttribute("createdOperator", operator);

        return "redirect:/";
    }

}
