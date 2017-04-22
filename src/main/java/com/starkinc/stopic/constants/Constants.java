package com.starkinc.stopic.constants;

public final class Constants {

	public static final String TOPIC_REF = "topicRef";

	// Error messages
	public static final String NO_RECORD_FOUND = "No record found";
	public static final String TOPIC_SEARCH = "Either topic or Username is mandatory";
	public static final String INVALID_REQUEST = "Request body is null or missing parameters";
	public static final String TOPIC_ALREADY_EXIST = "Topic already present";
	public static final String USER_ALREADY_EXIST = "User already present";
	public static final String TOKEN_EXPIRED = "Token has expired";
	
	public static final String TIME_INTERVAL = "${time.interval}";
	public static final String PRIVATE_KEY = "${private.key}"  ;
	public static final String HEADER_STRING = "${header.string}"  ;
	public static final String TOKEN_PREFIX = "${token.prefix}"  ;
	public static final String TIME_FACTOR = "${time.factor}"  ;
	public static final String SALT = "${salt}"  ;
	public static final String SECRECT_KEY = "${secret.key}"  ;
	
	private Constants() {
	}

}
