package com.blog.yeedawon.domain.member.controller;

import com.blog.yeedawon.domain.member.entity.Member;
import com.blog.yeedawon.domain.member.service.MemberService;
import com.blog.yeedawon.global.base.rsData.RsData;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
@RequestMapping("/member")
@AllArgsConstructor // lombok으로 생성자 주입 코드 생략
public class MemberController {

    private final MemberService memberService;

//    생성자 주입
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }

    // 1. 로그인 요청 처리 (인증 수행 및 쿠키 발급)
    @GetMapping("/login")
    public RsData login(String username, String password, HttpServletResponse resp) {
        // 서비스의 로그인 로직 호출
        RsData rsData = memberService.tryLogin(username, password);

        if (rsData.isSuccess()) {
            // 로그인 성공 시 응답 데이터에서 memberId를 추출하여 쿠키에 저장
            long memberId = (long) rsData.getData();
            Cookie cookie = new Cookie("loginedMemberId", String.valueOf(memberId));
            cookie.setPath("/"); // 모든 경로에서 쿠키 유효
            resp.addCookie(cookie);
        }
        return rsData;
    }

    @GetMapping("/me")
    public RsData showMe(HttpServletRequest req) {
        long loginMemberId = 0;
        if(req.getCookies() != null) {
            Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("loginedMemberId"))
                    .map(Cookie::getValue)
                    .mapToLong(Long::parseLong)
                    .findFirst()
                    .orElse(0);
        }
        boolean isLogined = loginMemberId > 0;

        if(!isLogined) {
            return RsData.of("F-1", "로그인 후 이용해주세요.");
        }

        Member member = memberService.findById(loginMemberId);
        return RsData.of("S-1", "당신의 username(은)는 '%s'입니다.".formatted());
    }

    @GetMapping("/logout")
    public RsData logout(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getCookies() != null) {
            Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("loginedMemberId"))
                    .forEach(cookie -> {
                        cookie.setMaxAge(0);
                        resp.addCookie(cookie);
                    });
        }
        return RsData.of("S-1", "로그아웃 되었습니다.");
    }
}
