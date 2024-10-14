package com.HS.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


// lombok의 @getter로 get 메소드, @RequiredArgsConstructor로 생성자가 자동 생성되는것을 확인
public class HelloResponseDtoTest {

    @Test
    public void lombok_test(){
        //given
        String name = "Test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
