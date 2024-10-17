package com.HS.springboot.web.dto;

import com.HS.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    //빌더 패턴은 객체의 생성 과정을 분리하여, 복잡한 객체를 단계적으로 만들어내는 방법입니다.
    //생성자나 setter 메서드 대신에,
    //빌더 객체를 통해 필요한 필드만 설정할 수 있고,
    //그 과정에서 매개변수의 순서와 오류를 방지할 수 있습니다.
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
