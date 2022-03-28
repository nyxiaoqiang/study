package com.study.boot.boot01hello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Boot01HelloApplicationTests {
	@Autowired
	Dog dog;

	@Autowired
	Person person;

	@Test
	void contextLoads() {
	}

	@Test
	void testYaml(){
		//System.out.println(dog);
		System.out.println(person);
	}
}
