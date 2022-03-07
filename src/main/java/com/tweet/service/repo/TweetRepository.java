package com.tweet.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tweet.service.document.TweetDocument;

@Repository(value = "TweetRepository")
public interface TweetRepository extends MongoRepository<TweetDocument, Long>{

	Optional<TweetDocument> findByTweetIdAndUserId(Long tweetId, Long userId);
	
	
	public List<TweetDocument> findByUserId(Long userId);
	
	@Aggregation(pipeline = { "{ '$group': { '_id' : '$userId' } }" })
	public List<Long> findDistinctUserId();
	
	 @Aggregation(pipeline = { "{ '$group': { '_id' : '$tweetMsg' } }" })
	 public List<String> findDistinctTweetMsg();
	 
	 public List<TweetDocument> findByTweetMsgIn(List<String> tweetMsg);

}
