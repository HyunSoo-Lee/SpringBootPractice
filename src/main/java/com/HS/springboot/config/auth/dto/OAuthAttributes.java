package com.HS.springboot.config.auth.dto;

import com.HS.springboot.domain.user.Role;
import com.HS.springboot.domain.user.User;
import lombok.Getter;
import lombok.Builder;

import java.util.Map;

// *** OAuth 흐름의 두번째 클래스(정확히는 OAuth to User Service 를 위한 객체) ***
// Google 또는 Naver 등의 외부 OAuth 서버에서 제공하는 사용자 정보를 '표준화'하는 역할.
// 외부 인증 서비스에서 받아온 데이터를 우리 애플리케이션에서 사용할 수 있는 객체(User 엔티티)로 변환.
// 이를 통해 OAuth 서버에서 전달된 복잡한 데이터 구조를 애플리케이션에서 쉽게 사용할 수 있도록 단순화, 표준화.

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    // 외부 인증 서비스로부터 전달받은 사용자 정보가 담긴 맵(Map) 객체
    // 이 안에 사용자의 이름, 이메일, 프로필 사진 등의 정보가 포함
    private String nameAttributeKey;
    // 인증 서비스에서 사용자를 식별하는 키값.
    // Ex) Google에서는 "sub", Naver에서는 "id"
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    // 외부 인증 서비스의 종류(registrationId, 예: Google, Naver)에 따라 적절한 방식으로 사용자 정보 생성(OAuthAttributes 객체).
    // Google과 Naver의 정보를 분기하여 처리함
    // 각각의 정보는 ofGoogle과 ofNaver 메서드를 통해 처리
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    //외부에서 받아온 사용자 정보를 기반으로, User 엔티티 객체를 생성하는 역할
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }

}
