package com.program;

import com.program.model.User;
import com.program.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

@SpringBootTest
class KPIAppApplicationTests {

	@Autowired
	private UserRepository userRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public KPIAppApplicationTests(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Test
	void contextLoads() {
		User user = userRepository.findByEmail("azat@gmail.com");
//		User newUser = user.get();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Test
	void newTest(){
		String input = "1/0.5/0,25";
		boolean hasSlash = input.contains("/");
		if (hasSlash) {
			String[] parts = input.split("/");
			String numberBeforeSlash = parts[2];
			System.out.println(numberBeforeSlash);
		} else {
			System.out.println(input);
		}

//		int result = Integer.parseInt(numberBeforeSlash);
//		System.out.println(result);

	}


}
