package com.springsecurity.service;

import com.springsecurity.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	public String getJwtToken(String username, String password) throws Exception {
		Authentication authentication = null;

		try {
			authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid Username or Password ", e);
		}

		final UserDetails ud = userDetailsService.loadUserByUsername(username);

		return JwtUtils.generateToken(ud, authentication);
	}
}
