package com.starkinc.stopic.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ServiceUtil {
	public static ResponseEntity<Object> buildEntity(HttpStatus status , Object entity){
		return new ResponseEntity<Object>(entity, status);
	}
	
	private ServiceUtil(){
	}
}
