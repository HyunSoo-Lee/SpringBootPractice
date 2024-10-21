package com.HS.springboot.config.auth.dto;

import com.HS.springboot.domain.user.User;
import lombok.Getter;


// *** OAuth 흐름의 세번째 클래스 ***

// User 엔티티에서 필요한 정보만을 추출해 세션에 저장할 수 있는 형태로 가볍게 관리
// User 엔티티는 데이터베이스와 관련된 복잡한 정보나 메서드를 포함하고 있을 수 있다
// SessionUser 인증된 사용자 정보만 필요로 함. 그 외에 필요한 정보는 없기때문에 name, email, picture 만 필드로 선언

@Getter
public class SessionUser {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
