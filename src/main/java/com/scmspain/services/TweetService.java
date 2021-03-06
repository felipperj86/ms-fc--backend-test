package com.scmspain.services;

import com.scmspain.jpa.TweetRepository;
import com.scmspain.jpa.entities.Tweet;
import org.springframework.boot.actuate.metrics.writer.Delta;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TweetService {
    private MetricWriter metricWriter;
    private TweetRepository repository;
    private TweetValidator validator = new TweetValidator();

    public TweetService(
            MetricWriter metricWriter,
            TweetRepository repository
    ) {
        this.metricWriter = metricWriter;
        this.repository = repository;
    }

    public void publishTweet(String publisher, String text) {
            Tweet tweet = new Tweet(publisher, text);
            validator.validate(tweet);
            this.metricWriter.increment(new Delta<Number>("published-tweets", 1));
            this.repository.save(tweet);
    }

    public List<Tweet> listAllTweets() {
        this.metricWriter.increment(new Delta<Number>("times-queried-tweets", 1));
        return repository.findByDiscardedDateIsNullAndPre2015MigrationStatusNotOrderByCreateDateDesc(99L);
    }

    public void discardATweet(Long tweetId){
        Tweet tweetToDiscard = this.repository.findOne(tweetId);
        if (tweetToDiscard == null) throw new IllegalArgumentException("Tweet id doesn't exist.");
        tweetToDiscard.setDiscardedDate(LocalDateTime.now());
        this.repository.save(tweetToDiscard);
    }

    public List<Tweet> listAllDiscardedTweets(){
        return repository.findByDiscardedDateIsNotNullOrderByDiscardedDateDesc();
    }
}