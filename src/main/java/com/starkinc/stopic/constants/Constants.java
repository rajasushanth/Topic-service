package com.starkinc.stopic.constants;

public final class Constants {

	public static final String TOPIC_REF = "topicRef";

	// Error messages
	public static final String NO_RECORD_FOUND = "No record found";
	public static final String TOPIC_SEARCH = "Either topic or Username is mandatory";
	public static final String INVALID_REQUEST = "Request body is null or missing parameters";
	public static final String TOPIC_ALREADY_EXIST = "Topic already present";
	public static final String USER_ALREADY_EXIST = "User already present";
	
	public static final String OPEN_TAG = "${";
	public static final String CLOSE_STAG = "}";
	public static final String TIME_INTERVAL_IN_DAYS = OPEN_TAG + "timeIntervalInDays" + CLOSE_STAG;
	public static final String PRIVATE_KEY = OPEN_TAG + "privateKey" + CLOSE_STAG;
	public static final String HEADER_STRING = OPEN_TAG + "headerString" + CLOSE_STAG;
	public static final String TOKEN_PREFIX = OPEN_TAG + "tokenPrefix" + CLOSE_STAG;
	
	private Constants() {
	}

}
