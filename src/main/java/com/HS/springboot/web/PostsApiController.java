package com.HS.springboot.web;

import com.HS.springboot.service.posts.PostsService;
import com.HS.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    //HTTP 메소드인 GET 의 요청을 받을 수 있는 API 만들어준다
    //이제 이 프로젝트는 /hello 로 요청이 오면 아래 함수를 보내주는 역할!
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }
}
