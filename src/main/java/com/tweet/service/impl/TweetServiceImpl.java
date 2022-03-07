package com.tweet.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.mongodb.client.MongoCursor;
import com.tweet.service.config.KafkaTemplateConfig;
import com.tweet.service.document.TweetDocument;
import com.tweet.service.document.UserDocument;
import com.tweet.service.dto.TweetDto;
import com.tweet.service.repo.TweetRepository;
import com.tweet.service.repo.UserRepository;

@Component
public class TweetServiceImpl implements TweetService{
	@Autowired
	private TweetRepository tweetRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private KafkaTemplateConfig producer;
	
	public void listenTweet(List<TweetDto> tweetDtoList) {
		for(TweetDto tweet:tweetDtoList) {
		Optional<TweetDocument> tweetAlreadyPresent=tweetRepo.findByTweetIdAndUserId(tweet.getTweetId(),tweet.getUserId());	
		if(!tweetAlreadyPresent.isPresent()) {
			TweetDocument tweetEnt= new TweetDocument();
			BeanUtils.copyProperties(tweet, tweetEnt);
			tweetRepo.insert(tweetEnt);
		}
		}
	}

	@Override
	public List<TweetDocument> viewMyTweets(Long userId) {
		List<TweetDocument> myTweets=tweetRepo.findByUserId(userId);
		if(!CollectionUtils.isEmpty(myTweets)) {
			return myTweets;
		}
		else return new ArrayList<>();
	}

	@Override
	public List<TweetDocument> viewAllTweets() {

		 List<String> tweetMsgs=tweetRepo.findDistinctTweetMsg();
		 List<TweetDocument> allTweets=tweetRepo.findByTweetMsgIn(tweetMsgs);
		 
		return allTweets;
	}

	@Override
	public List<UserDocument> viewAllUsers() {
		
		List<Long> userIds=tweetRepo.findDistinctUserId();
		List<UserDocument> allUsers=new ArrayList<>();
		if(!CollectionUtils.isEmpty(userIds)) {
			allUsers =userRepo.findByUserIdIn(userIds);
		}
		return allUsers;
		
	}

	@Override
	public String postTweet(TweetDto tweetDto) {
		Optional<TweetDocument> isTweetPresent=tweetRepo.findByTweetIdAndUserId(tweetDto.getTweetId(), tweetDto.getUserId());
		if(!isTweetPresent.isPresent()) {
			producer.sendMessage(tweetDto);
			return "Msg posted successfully";
		}
		else
		return "Duplicate Tweet msg";
	}
}
