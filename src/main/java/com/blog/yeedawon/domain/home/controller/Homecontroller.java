package com.blog.yeedawon.domain.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //스프링 부트에게 이 클래스는 Controller의 역할을 명시하는 annotation
public class Homecontroller {
    public int val = -1; // 메소드 안에서 지역변수로 실행시키면 =
    @GetMapping("/")
    @ResponseBody // 응답한 결과물을 body에 송신 (annotation)
    public String home() {
        return "Hello Bye!";
    }

    @GetMapping("/home/main1") // domain name의 하위 경로 설정 (localhost:8080/home/main1)
    @ResponseBody // 응답한 결과물을 body에 송신 (annotation)
    public String main1() {
        return "main1";
    }

    @GetMapping("/home/increase") // domain name의 하위 경로 설정 (localhost:8080/home/main2)
    @ResponseBody // 응답한 결과물을 body에 송신 (annotation)
    public int increase() {
        return ++val;
    }

    @GetMapping("/home/plus") // domain name의 하위 경로 설정 (localhost:8080/home/main2)
    @ResponseBody
    // 스프링 부트는 쿼리 스트링의 parameter를 method의 parameter로 받을 수 있다.
    // 규칙! : 쿼리 스트링의 parameter 변수명과 method의 parameter 변수 명을 동일하게 해야함
    public int showPlus(int a, int b) {
        return a + b;
    }
}
