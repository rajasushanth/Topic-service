package com.starkinc.stopic.dto;

import java.util.List;

import com.starkinc.stopic.constants.Constants;
import com.starkinc.stopic.entity.Topic;

/**
 * @author RajaSushanth
 *
 */
public class TopicsDTO {
	
	private List<Topic> topics;
	private long total;
	private long page;
	private boolean isFirst;
	private boolean isLast;
	
	public List<Topic> getTopics() {
		return topics;
	}
	public long getTotal() {
		return total;
	}
	public long getPage() {
		return page;
	}
	public boolean isFirst() {
		return isFirst;
	}
	public boolean isLast() {
		return isLast;
	}
	public TopicsDTO(List<Topic> topics, long total, long page) {
		super();
		this.topics = topics;
		this.total = total;
		this.page = page;
		if(total <= Constants.LIMIT * page){
			this.isLast = true;
		}
		if(page == 1){
			this.isFirst = true;
		}
	}
	
	
}
