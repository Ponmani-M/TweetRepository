package com.tweet.service.dto;

public class TweetDto {

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
	public TweetDto(Long tweetId, Long userId, String tweetMsg) {
		super();
		this.tweetId = tweetId;
		this.userId = userId;
		this.tweetMsg = tweetMsg;
	}
	public TweetDto() {
		super();
	}
}
