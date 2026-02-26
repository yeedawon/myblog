package com.blog.yeedawon.domain.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //스프링 부트에게 이 클래스는 Controller의 역할을 명시하는 annotation
public class Homecontroller {
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

    @GetMapping("/home/main2") // domain name의 하위 경로 설정 (localhost:8080/home/main2)
    @ResponseBody // 응답한 결과물을 body에 송신 (annotation)
    public String main2() {
        return "main2";
    }
}
