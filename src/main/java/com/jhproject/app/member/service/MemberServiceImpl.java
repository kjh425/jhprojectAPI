package com.jhproject.app.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhproject.app.member.dto.MemberSignUpDto;
import com.jhproject.app.member.entity.Member;
import com.jhproject.app.member.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberRepository memberRepository;
	
	
	@Override
	public List<Member> findAllMember() {
		List<Member> members = memberRepository.findAllMember();
		return members;
	}
	
	@Override
	public int signUpMember(MemberSignUpDto memberSignUpDto) {
		int result = 0;
		
		result = memberRepository.signUpMember(memberSignUpDto);
		return result;
	}
	
	@Override
	public Member findByMemberId(String memberId) {
		Member member = memberRepository.findByMemberId(memberId);
		return member;
	}
}
