package com.example.twitter.clone.repositories;

import com.example.twitter.clone.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterClonRepository extends JpaRepository<Tweet, Long> {
}