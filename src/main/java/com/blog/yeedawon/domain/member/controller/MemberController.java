package com.blog.yeedawon.domain.member.controller;

import com.blog.yeedawon.domain.member.service.MemberService;
import com.blog.yeedawon.global.base.rsData.RsData;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/member")
@AllArgsConstructor // lombok으로 생성자 주입 코드 생략
public class MemberController {

    private final MemberService memberService;

//    생성자 주입
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }

    @GetMapping("/login")
    public RsData login(String username, String password) {
        if(username == null || username.trim().isEmpty()) {
            return RsData.of("F-3", "아이디를 입력해주세요.");
        }
        if(password== null || password.trim().isEmpty()) {
            return RsData.of("F-4", "비밀번호를 입력해주세요.");
        }

        return memberService.tryLogin(username, password);
    }
}
