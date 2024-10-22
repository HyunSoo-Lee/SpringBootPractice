package com.HS.springboot.web;

import com.HS.springboot.config.auth.dto.SessionUser;
import com.HS.springboot.service.posts.PostsService;
import com.HS.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    // 게시글 관련 비즈니스 로직을 처리
    private final PostsService postsService;
    // 현재 사용자의 세션 정보를 저장하는 객체
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        // postService.findAllDesc()를 통해 게시글 목록을 조회하고, 결과를 모델에 담아 뷰로 전달함
        model.addAttribute("posts", postsService.findAllDesc());
        // 세션에서 SessionUser 객체를 가져와서, 로그인된 사용자일 시 사용자 이름을 모델에 추가하여 페이지에 사용자 이름을 표시할 수 있게 함
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
