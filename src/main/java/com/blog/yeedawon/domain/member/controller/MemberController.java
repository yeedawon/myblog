package com.blog.yeedawon.domain.member.controller;

import com.blog.yeedawon.domain.member.entity.Member;
import com.blog.yeedawon.domain.member.service.MemberService;
import com.blog.yeedawon.global.base.rq.Rq;
import com.blog.yeedawon.global.base.rsData.RsData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
@RequestMapping("/member")
@AllArgsConstructor // lombok으로 생성자 주입 코드 생략
@RequiredArgsConstructor // final 인스턴스 변수에 대해 dependency 부여 (조건 @Component + @RequiredScope annotation)
public class MemberController {

    private final MemberService memberService;
    private final Rq rq;

//    생성자 주입
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }

    @GetMapping("/login")
    public RsData login(String username, String password) {
        // 서비스의 로그인 로직 호출
        RsData rsData = memberService.tryLogin(username, password);

        if (rsData.isSuccess()) {
            // 로그인 성공 시 응답 데이터에서 memberId를 추출하여 쿠키에 저장
            Member member = (Member) rsData.getData();
            rq.setCookie("loginedMemberId", member.getId());
        }
        return rsData;
    }

    @GetMapping("/me")
    public RsData showMe() {
        long loginedMemberId = rq.getCookieAsLong("loginedMemberId", 0);

        boolean isLogined = loginedMemberId > 0;

        if(!isLogined) {
            return RsData.of("F-1", "로그인 후 이용해주세요.");
        }

        Member member = memberService.findById(loginedMemberId);
        return RsData.of("S-1", "당신의 username(은)는 %sd입니다.".formatted(member.getUsername()));
    }

    @GetMapping("/logout")
    public RsData logout() {

        boolean cookieRemoved = rq.removeCookie("loginedMemberId");

        if(!cookieRemoved) {
            return RsData.of("F-1", "로그아웃에 실패했습니다. 이미 로그아웃 상태입니다.");
        }

        return RsData.of("S-1", "로그아웃 되었습니다.");
    }
}
