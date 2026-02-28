package com.blog.yeedawon.domain.member.respositery;

import com.blog.yeedawon.domain.member.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {
    private List<Member> members;

    public MemberRepository() {
        this.members = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            // 회원 ID와 PW를 상이하게 설정 (예: user1/pw1, user2/pw2 ...)
            String username = "user" + i;
            String password = "1234" + i;

            // Member 객체 생성 시 생성자 파라미터 순서에 주의하세요!
            // (만약 Member 엔티티에 다른 필드들이 있다면 해당 값들도 추가해야 합니다.)
            Member member = new Member(username, password);
            this.members.add(member);
        }
    }

    public Member findByUsername(String username) {
        return members.stream()
                .filter(member1 -> member1.getUsername().equals(username))
                .findFirst().orElse(null);
    }

    public Member findById(long id) {
        return members.stream()
                .filter(member -> member.getId() == id)
                .findFirst().orElse(null);
    }
}
