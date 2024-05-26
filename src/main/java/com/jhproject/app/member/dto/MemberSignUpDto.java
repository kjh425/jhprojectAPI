package com.jhproject.app.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignUpDto {
	private String memberId;
    private String password;
    private String name;
    private String nick;
    private String email;
}
