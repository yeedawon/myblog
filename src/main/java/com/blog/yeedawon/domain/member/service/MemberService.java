package com.blog.yeedawon.domain.member.service;

import com.blog.yeedawon.domain.member.respositery.MemberRepository;
import com.blog.yeedawon.global.base.rsData.RsData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.blog.yeedawon.domain.member.entity.Member;

// 아래 클래스는 IoC container에 의해 생사소멸이 관리된다.
// @Component
// 데이터 판단 주체 (controller는 데이터 유효성 여부만 검증) 단, Service annotation이 있을 경우 자동으로 @Component가 호출됨
@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository repository;

    public Member findByUserName(String username) {
        return repository.findByUsername(username);
    }

    public RsData tryLogin(String username, String pw) {
        Member member = repository.findByUsername(username);

        if(member == null)
            return RsData.of("F-2", "%s은 존재하지 않는 회원입니다.".formatted(username));
        if(!member.getPassword().equals(pw))
            return RsData.of("F-2", "비밀번호가 일치하지 않습니다");
        return RsData.of("S-1", "%s님 환영합니다.".formatted(username), member.getId());
    }

    public Member findById(long id) {
        return repository.findById(id);
    }
}
