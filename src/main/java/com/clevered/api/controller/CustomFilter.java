package com.clevered.api.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CustomFilter extends OncePerRequestFilter {

	@Value("${auth.code}")
	private String authCode;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PythonCompilerController.class);


	public CustomFilter() {
		LOGGER.debug("CustomFilter is enabled!");
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