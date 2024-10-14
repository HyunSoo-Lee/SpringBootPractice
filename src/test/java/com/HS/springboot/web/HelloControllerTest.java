package com.HS.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
// 테스트 진행 시 JUnit 에 내장된 실행자 외 다른 실행자 실행
// -> Spring Runner 스프링 실행자를 실행한다는 뜻
// 즉, 스프링부트 테스트와 JUnit 사이의 연결자 역할

@WebMvcTest(controllers = HelloController.class)
// 스프링 테스트 어노테이션 중 Web 에 집중할 수 있는 어노테이션
// 컨트롤러 사용을 위해 선언했다.

public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;
    // mvc 테스트의 시작점, 이 클래스를 통해 HTTP Get, Post 등에 대한 API 테스트 가능

    @Test
    public void return_hello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))   //get 요청
                .andExpect(status().isOk())    //header status 확인 -> 200,404,500 등
                .andExpect(content().string(hello)); //응답 본문과 맞는지 검증
    }
}

