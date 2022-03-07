package com.tweet.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweet.service.document.TweetDocument;
import com.tweet.service.document.UserDocument;
import com.tweet.service.dto.TweetDto;
import com.tweet.service.impl.TweetService;

@RestController
@RequestMapping(value = "/app/tweet")
public class TweetController {

	@Autowired
	TweetService tweetService;

	@GetMapping(value = "/viewMyTweets")
	public ResponseEntity<List<TweetDocument>> viewMyTweets(@RequestParam Long userId) {
		List<TweetDocument> myTweets = tweetService.viewMyTweets(userId);
		return new ResponseEntity<List<TweetDocument>>(myTweets, HttpStatus.OK);
	}

	@GetMapping(value = "/viewAllTweets")
	public ResponseEntity<List<TweetDocument>> viewAllTweets() {
		List<TweetDocument> allTweets = tweetService.viewAllTweets();
		return new ResponseEntity<List<TweetDocument>>(allTweets, HttpStatus.OK);

	}
	
	@GetMapping(value = "/viewAllUsers")
	public ResponseEntity<List<UserDocument>> viewAllUsers() {
		List<UserDocument> allUsers = tweetService.viewAllUsers();
		return new ResponseEntity<List<UserDocument>>(allUsers, HttpStatus.OK);

	}
	
	@PostMapping(value = "/postTweet")
	public ResponseEntity<String> postTweet(@RequestBody TweetDto tweetDto) {
		String tweetPost = tweetService.postTweet(tweetDto);
		return new ResponseEntity<String>(tweetPost, HttpStatus.OK);

	}
}