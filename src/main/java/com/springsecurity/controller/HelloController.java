package com.springsecurity.controller;

import com.springsecurity.dto.AuthenticationRequest;
import com.springsecurity.service.LoginService;
import com.springsecurity.util.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

	@Autowired
	private LoginService loginService;

	@ResponseBody
	@PostMapping("/login")
	public JSONObject login(@RequestBody AuthenticationRequest authRequest) throws Exception {

		JSONObject json = new JSONObject();
		json.put("jwt", loginService.getJwtToken(authRequest.getUsername(), authRequest.getPassword()));

		return json;
	}

	@GetMapping("/home")
	public String show() {
		return "home";
	}

	@ResponseBody
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public JSONObject getAdmin() {
		JSONObject json = new JSONObject();
		json.put("status", "success admin!");

		return json;
	}

	@ResponseBody
	@GetMapping("/user")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public JSONObject getUser() {
		JSONObject json = new JSONObject();
		json.put("status", "success user!");

		return json;
	}
}
