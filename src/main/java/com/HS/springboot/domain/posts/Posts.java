package com.HS.springboot.domain.posts;

import com.HS.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
//매개변수가 없는 기본 생성자를 자동으로 만들어줌
//매개변수가 없는 기본 생성자란?
//생성자는 객체가 만들어질 때 그 객체의 초기 상태(필드 값)를 설정하는 역할을 합니다.
//생성자는 클래스의 객체(인스턴스)를 만들 때 호출되는 특별한 메서드입니다.
//자바에서 생성자의 이름은 클래스의 이름과 동일하며, 생성자는 리턴 타입이 없습니다.

@Entity //테이블과 매칭될 클래스임을 선언하는 어노테이션
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increment 설정용
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // Entity 클래스에서는 Setter 사용 금지.
    //대신, Setter가 없어도 값을 채워서 DB에 Insert 하는 기능을 만들기 위해
    //생성자를 만들어주어야 하는데.... 생성자 만드는 대신 @Builder 클래스를 사용하는 것이다.
    @Builder
    //빌더 패턴은 객체의 생성 과정을 분리하여, 복잡한 객체를 단계적으로 만들어내는 방법입니다.
    //생성자나 setter 메서드 대신에,
    //빌더 객체를 통해 필요한 필드만 설정할 수 있고,
    //그 과정에서 매개변수의 순서와 오류를 방지할 수 있습니다.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    //만약 NoArgsConstructor가 없다면?
    //public posts() {}
    //만약 이 클래스에서 기본 생성자가 없다면,
    //다른 클래스나 프레임워크(JPA 등)가 Posts 객체를 인스턴스화할 때 문제가 발생할 수 있습니다.
    //예를 들어, JPA는 엔티티 클래스를 생성할 때 기본 생성자를 필요로 하기 때문에, 기본 생성자가 없다면 오류가 발생합니다.

}
