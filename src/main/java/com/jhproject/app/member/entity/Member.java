package com.jhproject.app.member.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private String memberId; // 회원 아이디
    private String password;
    private String name;
    private String nickName;
    private String email;
	
}
