package com.HS.springboot.web;

import com.HS.springboot.domain.posts.Posts;
import com.HS.springboot.service.posts.PostsService;
import com.HS.springboot.web.dto.PostsListResponseDto;
import com.HS.springboot.web.dto.PostsResponseDto;
import com.HS.springboot.web.dto.PostsSaveRequestDto;
import com.HS.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    // 수정, 조회 기능
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @GetMapping("/api/v1/posts/list")
    public List<PostsListResponseDto> findAll() {
        return postsService.findAllDesc();
    }
}
