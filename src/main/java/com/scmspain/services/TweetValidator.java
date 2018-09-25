package com.scmspain.services;

import com.scmspain.jpa.entities.Tweet;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetValidator {


    private static final int TWEET_MAXIMUN_OF_CHARACTERES = 140;
    private static final String URL_PATTERN = "((https?):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]+( |$))*";
    private static final Pattern PATTERN = Pattern.compile(URL_PATTERN, Pattern.CASE_INSENSITIVE| Pattern.MULTILINE);

    public void validate(Tweet tweet){

        if(StringUtils.isEmpty(tweet.getPublisher())){
            throw new IllegalArgumentException("Publisher must not be null!");
        }

        if (StringUtils.isEmpty(tweet.getTweet())){
            throw new IllegalArgumentException("Tweet message must not be null!");
        }

        String msgWithoutUrl = removeUrl(tweet.getTweet());
        if (msgWithoutUrl.length() > TWEET_MAXIMUN_OF_CHARACTERES){
            throw new IllegalArgumentException("Tweet must not be greater than 140 characters!");
        }
    }


    private String removeUrl(String commentstr) {
        Matcher m = PATTERN.matcher(commentstr);
        return m.replaceAll("");
    }
}