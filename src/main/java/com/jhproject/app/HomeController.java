package com.jhproject.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhproject.app.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping(value = "/hello", produces = "application/json")
    @ResponseBody
    public List<String> Hello() {
        return Arrays.asList("반갑다 성공축하한다", "이 데이터는 스프링에서 보냈다.");
    }
}

