package com.starkinc.stopic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author RajaSushanth
 *
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class TopicServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopicServiceApplication.class, args);
	}
}
