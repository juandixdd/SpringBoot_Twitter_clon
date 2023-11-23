package com.example.twitter.clone.services;

import com.example.twitter.clone.ApiExceptionHandler.CustomExceptions.NotFoundException;
import com.example.twitter.clone.entities.Tweet;
import com.example.twitter.clone.repositories.TwitterClonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetService implements GlobalService<Tweet> {


    private final TwitterClonRepository twitterClonRepository;

    public TweetService(TwitterClonRepository twitterClonRepository) {
        this.twitterClonRepository = twitterClonRepository;
    }

    @Override
    @Transactional
    public List<Tweet> findAll() throws NotFoundException {
        try {
            return twitterClonRepository.findAll();
        } catch (Exception e) {
            throw new NotFoundException("Error al buscar todos los tweets: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Tweet findById(Long id) throws NotFoundException {

        Optional<Tweet> tweetOptional = twitterClonRepository.findById(id);
        return tweetOptional.orElseThrow(() -> new NotFoundException("No se encontró ningún tweet con el ID: " + id));
    }

    @Override
    @Transactional
    public Tweet save(Tweet tweet) {
        try {
            return twitterClonRepository.save(tweet);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el tweet: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Tweet update(Long id, Tweet tweet) throws NotFoundException {
        Optional<Tweet> existingTweet = twitterClonRepository.findById(id);

        if (existingTweet.isPresent()) {
            tweet.setId(id);
            try {
                return twitterClonRepository.save(tweet);
            } catch (Exception e) {
                throw new RuntimeException("Error al actualizar el tweet con ID: " + id);
            }
        } else {
            throw new NotFoundException("No se encontró ningún tweet con el ID: " + id);
        }
    }

    @Override
    @Transactional
    public Tweet delete(Long id) throws NotFoundException {
        Optional<Tweet> existingTweet = twitterClonRepository.findById(id);

        if (existingTweet.isPresent()) {
            try {
                Tweet deletedTweet = existingTweet.get();
                twitterClonRepository.deleteById(id);

                return deletedTweet;
            } catch (Exception e) {
                throw new RuntimeException("Error al eliminar el tweet con ID: " + id);
            }
        } else {
            throw new NotFoundException("No se encontró ningún tweet con el ID: " + id);
        }
    }
}


