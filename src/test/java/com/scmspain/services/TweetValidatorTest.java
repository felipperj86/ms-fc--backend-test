package com.scmspain.services;

import com.scmspain.jpa.entities.Tweet;
import org.junit.Assert;
import org.junit.Test;

public class TweetValidatorTest {

    @Test
    public void validateTweet(){
        TweetValidator validator = new TweetValidator();
        Tweet tweet = new Tweet("Crazy Name", "Test before https://www.google.es test after");
        try {
            validator.validate(tweet);
        } catch (IllegalArgumentException iae){
            Assert.fail("It shouldn't have any error!");
        }
    }

    @Test
    public void validateTweetPublisherNull(){
        TweetValidator validator = new TweetValidator();
        Tweet tweet = new Tweet(null, "Test before https://www.google.es test after");
        try{
            validator.validate(tweet);
        } catch (IllegalArgumentException iae){
            Assert.assertEquals("Publisher must not be null!", iae.getMessage());
        }
    }

    @Test
    public void validateTweetPublisherEmpty(){
        TweetValidator validator = new TweetValidator();
        Tweet tweet = new Tweet("", "Test before https://www.google.es test after");
        try{
            validator.validate(tweet);
        } catch (IllegalArgumentException iae){
            Assert.assertEquals("Publisher must not be null!", iae.getMessage());
        }
    }

    @Test
    public void validateTweetMessageNull(){
        TweetValidator validator = new TweetValidator();
        Tweet tweet = new Tweet("Any Name", null);
        try{
            validator.validate(tweet);
        } catch (IllegalArgumentException iae){
            Assert.assertEquals("Tweet message must not be null!", iae.getMessage());
        }
    }

    @Test
    public void validateTweetMessageEmpty(){
        TweetValidator validator = new TweetValidator();
        Tweet tweet = new Tweet("Any Name", "");
        try{
            validator.validate(tweet);
        } catch (IllegalArgumentException iae){
            Assert.assertEquals("Tweet message must not be null!", iae.getMessage());
        }
    }

    @Test
    public void validateTweetMessageWithMoreThan140Characteres(){
        TweetValidator validator = new TweetValidator();
        Tweet tweet = new Tweet("Any Name", "Lorem ipsum dolor sit amet, consecte tur adipiscing elit. Nulla a suscipit neque. Sed quis augue arcu. Nullam finibus eros mi, a turpis duis.");
        try{
            validator.validate(tweet);
            Assert.fail("It should have any error!");
        } catch (IllegalArgumentException iae){
            Assert.assertEquals("Tweet must not be greater than 140 characters!", iae.getMessage());
        }
    }

    @Test
    public void validateTweetMessageWithURLAndNoMoreThan140Characteres(){
        TweetValidator validator = new TweetValidator();
        Tweet tweet = new Tweet("Any Name", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. quis augue arcu. https://www.lipsum.com Nullam finibus a turpis duis.");
        try{
            validator.validate(tweet);
        } catch (IllegalArgumentException iae){
            Assert.fail("It shouldn't have any error!");
        }
    }


}