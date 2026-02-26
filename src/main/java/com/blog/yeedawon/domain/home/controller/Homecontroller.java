package com.blog.yeedawon.domain.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    // 각 변수 앞에 @RequestParam("default value")을 설정할 경우 해당 변수에 값이 대입되어있지 않을 때 default value로 초기화되어 메소드 실행
    // 주의! : @RequestParam(String), 기본값은 String이고, int 값이 아님
    public int showPlus(int a, @RequestParam("0") int b) {
        return a + b;
    }

    //path variable 방식
    @GetMapping("/home/plus2/{a}/{b}")
    @ResponseBody
    //각 변수 앞에 PathVariable annotation 추가해야함
    public int showPlus2(@PathVariable int a,@PathVariable int b) {
        return a + b;
    }
}
