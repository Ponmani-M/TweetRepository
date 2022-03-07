package com.tweet.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.tweet.service.repo.TweetRepository;

@SpringBootApplication
public class TweetServiceApplication {

	@Autowired
	TweetRepository tweetRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TweetServiceApplication.class, args);
	}

}
