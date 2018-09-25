package com.scmspain.jpa;

import com.scmspain.jpa.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findByDiscardedDateIsNullAndPre2015MigrationStatusNotOrderByCreateDateDesc(Long pre2015MigrationStatus);

    List<Tweet> findByDiscardedDateIsNotNullOrderByDiscardedDateDesc();
}
