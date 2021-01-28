package com.ssk.book.springboot.web;

import org.junit.Test;

import com.ssk.book.springboot.web.dto.HelloResponseDto;
import static org.assertj.core.api.Assertions.assertThat;
public class HelloResponseDtoTest {

	@Test
	public void lombokTest(){
		String name = "test";
		int amount = 100;
		
		HelloResponseDto dto = new HelloResponseDto(name,amount);
		
		assertThat(dto.getName()).isEqualTo(name);
		assertThat(dto.getAmount()).isEqualTo(amount);
	}
}
