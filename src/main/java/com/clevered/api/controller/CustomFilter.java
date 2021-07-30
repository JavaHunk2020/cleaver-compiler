package com.clevered.api.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
@Component
public class CustomFilter extends OncePerRequestFilter {

	@Value("${auth.code}")
	private String authCode;

	public CustomFilter() {
		System.out.println("#(*#&CustomFilter*@&@^#^");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String cauthCode = request.getHeader("Authorization");
		if (!authCode.equals(cauthCode)) {
			response.sendError(401, "Auth code failed!");
			return;
		} 
		filterChain.doFilter(request, response);
	}
}