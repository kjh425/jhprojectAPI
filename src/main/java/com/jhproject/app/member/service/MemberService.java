package com.jhproject.app.member.service;

import java.util.List;

import com.jhproject.app.member.dto.MemberSignUpDto;
import com.jhproject.app.member.entity.Member;

public interface MemberService {


	List<Member> findAllMember();

	int signUpMember(MemberSignUpDto memberSignUpDto);

	Member findByMemberId(String memberId);
	
}
