package com.HS.springboot.config.auth;

import com.HS.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Service;

// *** OAuth 흐름 클래스 0번째 ***
// 전체 보안 설정과 OAuth 인증 흐름을 관리
// Spring Security 의 보안 설정을 담당
// 여기서 사용자가 OAuth2 로그인을 시도하면, 설정된 OAuth2 로그인 처리 로직이 실행

@RequiredArgsConstructor
@Service
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
                //해당 설정을 통해 OAuth 로그인 과정에서 사용자 정보 처리를 위해 CustomOAuth2UserService 클래스 호출
    }
}
