package com.starkinc.stopic.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.starkinc.stopic.entity.Topic;

public final class ServiceUtil {
	public static ResponseEntity<Object> buildEntity(HttpStatus status, Object entity) {
		return new ResponseEntity<Object>(entity, status);
	}

	public static List<String> getTopicNames(List<Topic> topics) {
		List<String> topicName = new ArrayList<>();
		for (Topic topic : topics) {
			topicName.add(topic.getTopicName());
		}
		return topicName;
	}

	private ServiceUtil() {
	}
}
