package com.blog.yeedawon.domain.member.service;

import com.blog.yeedawon.domain.member.respositery.MemberRepository;
import com.blog.yeedawon.global.base.rsData.RsData;
import org.springframework.stereotype.Service;

import com.blog.yeedawon.domain.member.entity.Member;


// 데이터 판단 주체 (controller는 데이터 유효성 여부만 검증)
@Service
public class MemberService {
    private MemberRepository repository;

    public MemberService() {
        this.repository = new MemberRepository();
    }

    public RsData tryLogin(String username, String pw) {
        Member member = repository.findByUsername(username);

        if(member == null)
            return RsData.of("F-2", "%s은 존재하지 않는 회원입니다.".formatted(username));
        if(!member.getPassword().equals(pw))
            return RsData.of("F-2", "비밀번호가 일치하지 않습니다");
        return RsData.of("S-1", "%s님 환영합니다.".formatted(username));
    }
}
