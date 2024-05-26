package com.jhproject.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;
import com.jhproject.app.member.entity.Member;
import com.jhproject.app.member.entity.MemberDetails;
import com.jhproject.app.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService{
	

	@Autowired
	private MemberService memberService;
	
	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
	System.out.println("내가뭘받았냐면");
	System.out.println(memberId);
	System.out.println("를 받았단다");
    Member member = memberService.findByMemberId(memberId);
    log.debug("custom입니다 받아왔나요?={}",member);
    if (member == null) {
        throw new UsernameNotFoundException("User not found with username: " + memberId);
        
    }
    
    log.debug("로그인객체 = {}", member);
    
    return MemberDetails.builder()
        .memberId(member.getMemberId())
        .nickName(member.getNickName())
        .name(member.getName())
        .email(member.getEmail())
        .password(member.getPassword())
        // 다른 필드 설정
        .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
        .build();
	}
}
