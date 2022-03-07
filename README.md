# TweetRepository
Tweet Repository is used for posting a tweet, view my tweet, view all tweets and view all users.

Post a tweet-> user will be posting a tweet and it send to kafka messanger 
This message will be listened by kafka listener and stored in mongo db.
Here we are using kafka listener to build real-time streaming data pipelines and real-time streaming applications.
Also many consumers listen parallely.

View my tweet -> user will be able to see all his/her tweets
view all tweets -> user will be able to see everyone tweets
view all users -> user will be able to see everyone who has tweet account
