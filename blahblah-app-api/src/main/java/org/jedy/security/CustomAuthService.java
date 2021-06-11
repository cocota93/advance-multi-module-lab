package org.jedy.security;

import org.jedy.member.exception.MemberNotFindException;
import org.jedy.member.domain.Member;
import org.jedy.member.repository.MemberRepository;
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

    @Autowired private MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member loginMember = memberRepository.findFetchAuthByLoginId(username)
                .orElseThrow(() -> new MemberNotFindException(username));
        List<SimpleGrantedAuthority> authorityList = loginMember.getAuthorityList().stream()
                .map(auth -> new SimpleGrantedAuthority(auth.getType().getAuthority()))
                .collect(Collectors.toList());

        return new User(loginMember.getLoginId(), loginMember.getPassword(), authorityList);
    }
}
