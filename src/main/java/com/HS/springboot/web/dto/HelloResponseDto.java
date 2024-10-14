package com.HS.springboot.web.dto;

// Data Transfer Object
// 스프링에서는 일반적으로 컨트롤러에서 서비스로, 또는 서비스에서 컨트롤러로 데이터를 주고받을 때 사용


import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 엔티티와 분리:
// 데이터베이스와 직접적으로 연결된 엔티티(Entity) 객체와는 달리,
// DTO는 비즈니스 로직과 데이터베이스 구조에 영향을 받지 않도록 분리된 객체입니다.
// 따라서 엔티티를 직접 외부로 노출하지 않고, 필요한 데이터만 추려서 클라이언트에게 전송할 수 있습니다.

// 데이터 캡슐화:
// 특정 데이터를 묶어서 한 번에 전달할 수 있도록 합니다.

// 계층 간 데이터 전송:
// 컨트롤러, 서비스, 레포지토리 등 애플리케이션의 여러 계층 사이에서 데이터를 주고받을 때 사용됩니다.

@Getter //선언된 모든 필드의 get 메소드 생성
@RequiredArgsConstructor //선언된 모든 final 필드가 포함된 생성자를 생성, final 이 없는 필드는 생성자에 포함 X
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
