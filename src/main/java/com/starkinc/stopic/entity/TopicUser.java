package com.starkinc.stopic.entity;

import org.springframework.data.annotation.Id;

/**
 * @author RajaSushanth
 *
 */
public class TopicUser {
	
	@Id
	private String username;
	private String password;
	private boolean isEnabled;
	private String role;
	private String question;
	private String answer;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TopicUser [username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", isEnabled=");
		builder.append(isEnabled);
		builder.append(", role=");
		builder.append(role);
		builder.append(", question=");
		builder.append(question);
		builder.append(", answer=");
		builder.append(answer);
		builder.append("]");
		return builder.toString();
	}	
}
