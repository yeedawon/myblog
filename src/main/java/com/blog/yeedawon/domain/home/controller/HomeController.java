package com.blog.yeedawon.domain.home.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // ResponseBody + Controller
public class HomeController {
    public int val = -1;
    public List<Person> list = new ArrayList<>();

    @GetMapping("/") // root
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

    @GetMapping("/home/returnMember")
    public List<Member> showReturnMember() {
        Member member1 = new Member(
                "홍길동",
                30,
                "고양",
                false,
                "test1@exmaple.com",
                "010-1234-5678",
                List.of("게임", "독서", "헬스", "탁구")
        );

        Member member2 = new Member(
                "홍길순",
                31,
                "군포",
                true,
                "test2@exmple.com",
                "010-0987-6543",
                List.of("런닝", "피아노")
        );
        List<Member> list = new ArrayList<>();
        list.add(member1);
        list.add(member2);
        return list;
    }

    @GetMapping("/home/addPerson")
    public String addPerson(String name, int age) {
        Person p = new Person(name, age);
        this.list.add(p);
        return "%d번 사람이 추가되었습니다.".formatted(p.getId());
    }

    @GetMapping("/home/makePersonData")
    public String addPerson() {
        list.add(new Person("홍길동", 11));
        list.add(new Person("김길동", 22));
        list.add(new Person("장길동", 33));
        return "데이터 생성 완료";
    }

    @GetMapping("/home/showPerson")
    public List<Person> showPeople() {
        if(this.list.isEmpty()) {
            return null;
        }
        return this.list;
    }

    @GetMapping("/home/removePerson")
    public String removePerson(int id) {
        /*
        Person foundPerson = null;
        for (Person person : list) {
            if(person.getId() == id) {
                foundPerson = person;
                break;
            }
        }
        if(foundPerson == null)
            return "%d번 사람은 존재하지 않습니다.".formatted(id);
        list.remove(foundPerson);
        return "%d번 사람의 데이터가 삭제되었습니다.".formatted(id);
        */

        // 스트림 방식
        /*
        Person foundPerson = list.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        if(foundPerson == null)
            return "%d번 사람을 찾을 수 없습니다.".formatted(id);
        list.remove(foundPerson);
        return "%d번 사람이 삭제되었습니다.".formatted(id);
         */

        //v3
        boolean removed = list.removeIf(p -> p.getId() == id);
        if(!removed)
            return "%d번 사람을 찾을 수 없습니다.".formatted(id);
        return "%d번 사람이 삭제되었습니다.".formatted(id);
    }

    @GetMapping("/home/modifyPerson")
    public String modifyPerson(int id, String name, int age) {
        Person foundperson = list.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        if(foundperson == null)
            return "%d번 사람이 없습니다.".formatted(id);
        foundperson.setName(name);
        foundperson.setAge(age);
        return "%d번 사람의 정보가 수정되었습니다".formatted(id);
    }

    @GetMapping("/home/cookie/increase") // domain name의 하위 경로 설정 (localhost:8080/home/main2)
    public int showCookieIncrease(HttpServletRequest req, HttpServletResponse resp) {
//    HttpServletResponse : 받은 편지
//    HttpServletResponse : 보낼 편지

        // 최초에는 쿠기가 존재하지 않으므로
        int countInCookie = 0;
        if(req.getCookies() != null) {
            countInCookie = Arrays.stream(req.getCookies()) //쿠키의 배열을 스트림으로
                    .filter(cookie -> cookie.getName().equals("count")) // 쿠키명이 "count"인 것만 필터링
                    .map(Cookie::getValue) // 쿠키로부터 값을 가져오기
                    .mapToInt(Integer::parseInt) // cookieValue(String -> int)
                    .findFirst().orElse(0);
        }

        int newCountInCookie = countInCookie + 1;

        resp.addCookie(new Cookie("count", newCountInCookie + ""));
        return countInCookie;
    }
}
/* Member 클래스의 인스턴스 변수를 private으로 설정한 이유
1. 데이터 취급권한 설정으로 보안성 향상
2. Spring boot의 라이브러리인 jackson의 역할 중
   객체 내부의 정보를 getter와 setter 기반으로 직렬화(Java obj ---> JSON)을 수행하기 때문
   cf) (server(Java) <---> (translation by JSON) <---> web(JavaScript))의 방식으로 서버와 웹브라우저 사이에서 작동함
*/
class Member {
    private String name;
    private int age;
    private String address;
    private boolean isMarried;
    private String email;
    private String phone;
    private List<String> hobbies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Member(String name, int age, String address, boolean isMarried, String email, String phone, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.isMarried = isMarried;
        this.email = email;
        this.phone = phone;
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", isMarried=" + isMarried +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", hobbies=" + hobbies +
                '}';
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Person {
    private static int lastId;
    private int id;
    private String name;
    private int age;

    public Person(String name, int age) {
        this(lastId++, name, age);
    }
}
