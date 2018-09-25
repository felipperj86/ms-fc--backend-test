package com.scmspain.configuration;

import com.scmspain.controller.TweetController;
import com.scmspain.jpa.TweetRepository;
import com.scmspain.services.TweetService;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;

@Configuration
public class TweetConfiguration {
    @Bean
    public TweetService getTweetService(MetricWriter metricWriter, TweetRepository repository) {
        return new TweetService(metricWriter, repository);
    }

    @Bean
    public TweetController getTweetConfiguration(TweetService tweetService) {
        return new TweetController(tweetService);
    }
}
