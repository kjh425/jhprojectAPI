package com.jhproject.app.member.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jhproject.app.member.dto.MemberSignInDto;
import com.jhproject.app.member.dto.MemberSignUpDto;
import com.jhproject.app.member.entity.Member;
import com.jhproject.app.member.entity.MemberDetails;
import com.jhproject.app.member.service.MemberService;
import com.jhproject.app.security.CustomUserDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Value("${jwt.secret-key}")
    private String jwtSecretKey;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@PostMapping("/sendCode.do")
	public ResponseEntity<?> sendVerificationCode(
			@RequestParam("email") String email
			){
		log.debug("받아왔는가?={}",email);
		String randomCode = null;
		int min = 100000;
		int max = 999999;
		Random random = new Random();
		int randomNumber = random.nextInt(max - min + 1) + min;
		randomCode = String.valueOf(randomNumber);

		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("k0j4h25@gmail.com");
		message.setTo(email);
		message.setSubject("회원가입 인증코드를 발송합니다.");
		message.setText(randomCode);

		javaMailSender.send(message);
		

	    return ResponseEntity.status(HttpStatus.OK)
	            .body(randomCode);
	}
	
	@PostMapping("/signUp.do")
    public ResponseEntity<String> signUpMember(
            @RequestBody MemberSignUpDto memberSignUpDto
    ) {
        // 패스워드를 BCrypt로 인코딩하여 저장
        String encodedPassword = passwordEncoder.encode(memberSignUpDto.getPassword());
        memberSignUpDto.setPassword(encodedPassword);

        // 나머지 회원 가입 로직
        int result = memberService.signUpMember(memberSignUpDto);

        if (result == 1) {
            String resultMsg = "회원가입이 완료되었습니다.";
            return ResponseEntity.ok(resultMsg);
        } else {
            String resultMsg = "뭔가 이상해요! 회원가입 오류~~";
            return ResponseEntity.ok(resultMsg);
        }
    }

	
	@PostMapping("/signIn")
	public ResponseEntity<Map<String, Object>> signInMember(@RequestBody MemberSignInDto memberSignInDto) {
	    log.debug("ㅋㅋ 받긴했냐?={}", memberSignInDto);
	    MemberDetails userDetails = (MemberDetails) customUserDetailsService.loadUserByUsername(memberSignInDto.getMemberId());

	    String memberId = userDetails.getMemberId();
	    String username = userDetails.getName();
	    String email = userDetails.getEmail();
        String token = Jwts.builder()
                .setSubject(memberId)
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)// 이 부분에서 서명에 사용할 키를 설정해야 합니다.
                .compact();

        // 사용자 정보를 생성
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("memberId", memberId);
        userInfo.put("username", username);
        userInfo.put("email", email);

        // 토큰과 사용자 정보를 응답으로 반환합니다.
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", userInfo);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.setAuthenticated(true);

        return ResponseEntity.ok(response);
	}


}
