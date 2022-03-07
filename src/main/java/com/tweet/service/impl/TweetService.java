package com.tweet.service.impl;

import java.util.List;

import com.tweet.service.document.TweetDocument;
import com.tweet.service.document.UserDocument;
import com.tweet.service.dto.TweetDto;

public interface TweetService {

	public List<TweetDocument> viewMyTweets(Long userId);
	
	public List<TweetDocument> viewAllTweets();

	public List<UserDocument> viewAllUsers();

	public String postTweet(TweetDto tweetDto);
}
