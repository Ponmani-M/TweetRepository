package com.tweet.service.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.tweet.service.dto.TweetDto;
import com.tweet.service.impl.TweetServiceImpl;


@Service
public class KafkaListenerMsg {

	@Autowired
	private TweetServiceImpl tweetServiceImpl;
	
	 @KafkaListener(topics = "${kafka.tweetTopic}")
	    public void receive(List<TweetDto> tweetDtoList) {
		 tweetServiceImpl.listenTweet(tweetDtoList);
	 }
}