package com.starkinc.stopic.entity;

import org.springframework.http.HttpStatus;

public class Error {
	private HttpStatus status;
	private String message;
	public HttpStatus getCode() {
		return status;
	}
	public void setCode(HttpStatus code) {
		this.status = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Error(HttpStatus code, String message) {
		super();
		this.status = code;
		this.message = message;
	}

}
