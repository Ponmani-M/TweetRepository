package com.tweet.service.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("TWEET")
public class TweetDocument {
	
	@Transient
    public static final String SEQUENCE_NAME = "tweet_sequence";
	
	@Id
	private Long tweetId;
	
	private Long userId;
	private String tweetMsg;
	
	public Long getTweetId() {
		return tweetId;
	}
	public void setTweetId(Long tweetId) {
		this.tweetId = tweetId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTweetMsg() {
		return tweetMsg;
	}
	public void setTweetMsg(String tweetMsg) {
		this.tweetMsg = tweetMsg;
	}
	public TweetDocument(Long tweetId, Long userId, String tweetMsg) {
		super();
		this.tweetId = tweetId;
		this.userId = userId;
		this.tweetMsg = tweetMsg;
	}
	public TweetDocument() {
		super();
	}
	
	
}