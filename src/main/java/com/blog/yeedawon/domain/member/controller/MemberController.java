package com.blog.yeedawon.domain.member.controller;

import com.blog.yeedawon.domain.member.service.MemberService;
import com.blog.yeedawon.global.base.rsData.RsData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = new MemberService();
    }

    @GetMapping("/login")
    public RsData login(String id, String pw) {
        memberService.tryLogin(id, pw);
        return RsData.of("msg", "%s님 환영합니다.".formatted(id));
    }
}
