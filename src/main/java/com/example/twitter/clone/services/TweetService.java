package com.example.twitter.clone.services;

import com.example.twitter.clone.ApiExceptionHandler.CustomExceptions.NotFoundException;
import com.example.twitter.clone.entities.Tweet;
import com.example.twitter.clone.repositories.TweetRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetService implements GlobalService<Tweet> {


    private final TweetRepository tweetRepository;

    public TweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    @Transactional
    public List<Tweet> findAll() throws NotFoundException {
        try {
            return tweetRepository.findAll();
        } catch (Exception e) {
            throw new NotFoundException("Error al buscar todos los tweets: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Tweet findById(Long id) throws NotFoundException {

        Optional<Tweet> tweetOptional = tweetRepository.findById(id);
        return tweetOptional.orElseThrow(() -> new NotFoundException("No se encontró ningún tweet con el ID: " + id));
    }

    @Override
    @Transactional
    public Tweet save(Tweet tweet) {
        try {
            return tweetRepository.save(tweet);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el tweet: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Tweet update(Long id, Tweet tweet) throws NotFoundException {
        Optional<Tweet> existingTweet = tweetRepository.findById(id);

        if (existingTweet.isPresent()) {
            tweet.setId(id);
            try {
                return tweetRepository.save(tweet);
            } catch (Exception e) {
                throw new RuntimeException("Error al actualizar el tweet con ID: " + id);
            }
        } else {
            throw new NotFoundException("No se encontró ningún tweet con el ID: " + id);
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        if (tweetRepository.existsById(id)) {
            try {
                tweetRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                throw new Exception("Error al eliminar el tweet con el ID: " + id);
            }
        } else {
            throw new Exception("No se encontró ningún tweet con el ID: " + id);
        }
    }
}


