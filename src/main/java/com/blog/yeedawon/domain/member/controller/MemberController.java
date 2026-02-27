package com.blog.yeedawon.domain.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    @GetMapping("/login")
    public String login(String id, String password) {
        return "로그인";
    }
}
