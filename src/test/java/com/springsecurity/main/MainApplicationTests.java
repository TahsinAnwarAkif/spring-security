package com.springsecurity.main;

import com.springsecurity.service.CustomUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
class MainApplicationTests {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Test
	public void validateUserFetchingByUsername() {
		UserDetails ud = userDetailsService.loadUserByUsername("akif-adm");

		Assertions.assertEquals("akif-adm", ud.getUsername());
	}
}
