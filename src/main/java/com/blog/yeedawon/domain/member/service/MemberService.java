package com.blog.yeedawon.domain.member.service;

import com.blog.yeedawon.global.base.rsData.RsData;
import org.springframework.stereotype.Service;

// 데이터 판단 주체 (controller는 데이터 유효성 여부만 검증)
@Service
public class MemberService {

    public RsData tryLogin(String id, String pw) {
        if(!pw.equals("1234"))
            return RsData.of("F-2", "비밀번호가 일치하지 않습니다");
        if(!id.equals("user1"))
            return RsData.of("F-2", "%s은 존재하지 않는 회원입니다.".formatted(id));
        return RsData.of("S-1", "%s님 환영합니다.".formatted(id));
    }
}
