package com.blog.yeedawon.domain.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // ResponseBody + Controller
public class Homecontroller {
    public int val = -1; // 메소드 안에서 지역변수로 실행시키면 =
    @GetMapping("/")
    public String home() {
        return "Hello Bye!";
    }

    @GetMapping("/home/main1") // domain name의 하위 경로 설정 (localhost:8080/home/main1)
    public String main1() {
        return "main1";
    }

    @GetMapping("/home/increase") // domain name의 하위 경로 설정 (localhost:8080/home/main2)
    public int increase() {
        return ++val;
    }

    @GetMapping("/home/plus") // domain name의 하위 경로 설정 (localhost:8080/home/main2)
    // 스프링 부트는 쿼리 스트링의 parameter를 method의 parameter로 받을 수 있다.
    // 규칙! : 쿼리 스트링의 parameter 변수명과 method의 parameter 변수 명을 동일하게 해야함
    // 각 변수 앞에 @RequestParam("default value")을 설정할 경우 해당 변수에 값이 대입되어있지 않을 때 default value로 초기화되어 메소드 실행
    // 주의! : @RequestParam(String), 기본값은 String이고, int 값이 아님
    public int showPlus(int a, @RequestParam("0") int b) {
        return a + b;
    }

    //path variable 방식
    @GetMapping("/home/plus2/{a}/{b}")
    //각 변수 앞에 PathVariable annotation 추가해야함
    public int showPlus2(@PathVariable int a,@PathVariable int b) {
        return a + b;
    }

    @GetMapping("/home/returnBoolean")
    public boolean showReturnBoolean() {
        return true;
    }

    @GetMapping("/home/returnDouble")
    public double showReturnDouble() {
        return Math.PI;
    }

    @GetMapping("/home/returnArray")
    public int[] showReturnArray() {
        int[] array = {2, 5, 7, 1, 10};
        return array;
    }

    @GetMapping("/home/returnList")
    public List<Integer> showReturnList() {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            list.add(i);
        }
        return list;
    }

    @GetMapping("/home/returnMap")
    //웹에서 {}로 표시되는 이유 : javaScript에서는 Map을 "객체"로 인식 + 객체 - 중괄호{} 형태로 표현
    public Map<String, Object> showReturnMap() {
//        Map<String, Object> map = Map.of(
//                "name", "홍길동"
//                ,"age", 30
//                ,"address", "화정시"
//                ,"isMarried", false
//                ,"email", "test1@exmaple.com"
//                ,"phone", "010-1234-5678"
//                ,"hobbies", List.of("게임", "독서", "헬스", "탁구")
//        );
        Map<String, Object> map = new LinkedHashMap<>() {{
            put("name", "홍길동");
            put("age", 30);
            put("address", "고양");
            put("isMarried", false);
            put("email", "test1@exmaple.com");
            put("phone", "010-1234-5678");
            put("hobbies", List.of("게임", "독서", "헬스", "탁구"));
        }};
        return map;
    }
}
