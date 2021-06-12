package org.jedy.security;

import org.jedy.operator.domain.Operator;
import org.jedy.operator.exception.OperatorLoginAuthorityAssignInvalidException;
import org.jedy.operator.exception.OperatorLoginIdNotFoundException;
import org.jedy.operator.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomAuthService implements UserDetailsService {

    @Autowired private OperatorRepository operatorRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Operator loginOperator = operatorRepository.findFetchAuthByLoginId(username)
                                                             .orElseThrow(() -> new OperatorLoginIdNotFoundException(username));
        List<SimpleGrantedAuthority> authorityList = loginOperator.getAuthorityList().stream()
                .map(auth -> new SimpleGrantedAuthority(auth.getType().getAuthority()))
                .collect(Collectors.toList());

        if(authorityList.isEmpty()){
            throw new OperatorLoginAuthorityAssignInvalidException(username);
        }

        return new User(loginOperator.getLoginId(), loginOperator.getPassword(), authorityList);
    }
}
