package org.jedy.security;

import org.jedy.operator.domain.Operator;
import org.jedy.operator.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomAuthService implements UserDetailsService {

    @Autowired private OperatorRepository operatorRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Operator loginOperator = operatorRepository.findFetchAuthByLoginId(username);
        List<SimpleGrantedAuthority> authorityList = loginOperator.getAuthorityList().stream()
                .map(auth -> new SimpleGrantedAuthority(auth.getType().getAuthority()))
                .collect(Collectors.toList());

        return new User(loginOperator.getLoginId(), loginOperator.getPassword(), authorityList);
    }
}
