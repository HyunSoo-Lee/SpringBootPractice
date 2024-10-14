package com.HS.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//컨트롤러를 JSON을 반환하는 컨트롤러로 바꿔준다

public class HelloController {
    @GetMapping("/hello")
    //HTTP 메소드인 GET의 요청을 받을 수 있는 API 만들어준다
    //이제 이 프로젝트는 /hello로 요청이 오면 문자열 hello를 반환하는 기능을 가지게 된다.
    public String hello(){
        return "hello";
    }
}
