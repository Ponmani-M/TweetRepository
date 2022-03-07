package com.tweet.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.tweet.service.dto.TweetDto;

public class KafkaTemplateConfig {
    @Value(value = "${kafka.tweetTopic}")
    private String tweetTopic;

	@Autowired
	private KafkaTemplate<String, TweetDto> kafkaTemplate;

	public void sendMessage(TweetDto message) {
        
	    this.kafkaTemplate.send(tweetTopic, message);
		
	}
}