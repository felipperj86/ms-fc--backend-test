package com.scmspain.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Tweet {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String tweet;

    @Column (nullable = true)
    private Long pre2015MigrationStatus = 0L;

    @JsonIgnore
    @Column (nullable = false)
    private LocalDateTime createDate;

    @JsonIgnore
    @Column (nullable = true)
    private LocalDateTime discardedDate;

    public Tweet() {

    }

    public Tweet(String publisher, String tweet) {
        this.publisher = publisher;
        this.tweet = tweet;
        this.createDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getTweet() {
        return tweet;
    }

    public Long getPre2015MigrationStatus() {
        return pre2015MigrationStatus;
    }

    public void setDiscardedDate(LocalDateTime discardedDate) {
        this.discardedDate = discardedDate;
    }
}
