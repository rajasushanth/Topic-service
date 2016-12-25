package com.starkinc.stopic.entity;

import org.springframework.http.HttpStatus;

public class Error {
	private HttpStatus code;
	private String message;
	public HttpStatus getCode() {
		return code;
	}
	public void setCode(HttpStatus code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Error(HttpStatus code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

}
