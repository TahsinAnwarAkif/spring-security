package com.springsecurity.filter;

import com.springsecurity.util.JwtUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {

		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);

			return;
		}

		String jwt = authHeader.substring(7);

		String username = JwtUtils.extractUsername(jwt);
		List<Map<String, String>> authorityList = JwtUtils.getAuthorityList(jwt);
		Set<SimpleGrantedAuthority> simpleGrantedAuthorityList = authorityList
			.stream()
			.map(m -> new SimpleGrantedAuthority(m.get("authority")))
			.collect(Collectors.toSet());

		if (JwtUtils.validateToken(jwt)) {
			UsernamePasswordAuthenticationToken usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(username,
				null, simpleGrantedAuthorityList);
			usernamePasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthToken);
		}

		filterChain.doFilter(request, response);
	}
}
