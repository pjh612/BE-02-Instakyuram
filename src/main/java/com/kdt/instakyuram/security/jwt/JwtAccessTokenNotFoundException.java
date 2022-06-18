package com.kdt.instakyuram.security.jwt;

public class JwtAccessTokenNotFoundException extends RuntimeException {
	public JwtAccessTokenNotFoundException() {
	}

	public JwtAccessTokenNotFoundException(String message) {
		super(message);
	}

	public JwtAccessTokenNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
