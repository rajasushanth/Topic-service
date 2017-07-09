package com.starkinc.stopic.entity;

import org.springframework.http.HttpStatus;

/**
 * @author RajaSushanth
 *
 */
public class Error {
	
	private long timestamp = System.currentTimeMillis();
	private int status;
	private HttpStatus error;
	private String message;
	private String path;
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public HttpStatus getError() {
		return error;
	}
	public void setError(HttpStatus error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Error(long timestamp, int status, HttpStatus error, String message, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}
	
	public Error(HttpStatus error, String message) {
		super();
		this.error = error;
		this.message = message;
		this.status = error.value();
	}
	public Error() {
	}
	
	

}
