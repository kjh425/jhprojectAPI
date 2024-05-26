package com.jhproject.app.member.repository;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.jhproject.app.member.dto.MemberSignUpDto;
import com.jhproject.app.member.entity.Member;

@Mapper
public interface MemberRepository{

	@Select("select * from member")
	List<Member> findAllMember();

	@Insert("insert into member values(#{memberId},#{password},#{name},#{nick},#{email})")
	int signUpMember(MemberSignUpDto memberSignUpDto);

	@Select("select * from member where member_id=#{memberId}")
	Member findByMemberId(String memberId);

	
}
