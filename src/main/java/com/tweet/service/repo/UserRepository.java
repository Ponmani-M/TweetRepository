package com.tweet.service.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweet.service.document.UserDocument;


public interface UserRepository extends MongoRepository<UserDocument, Long>{

	public List<UserDocument> findByUserIdIn(List<Long> userId);

}