package com.example.twitter.clone.controllers;

import com.example.twitter.clone.ApiExceptionHandler.CustomExceptions.UserNotFoundException;
import com.example.twitter.clone.entities.Tweet;
import com.example.twitter.clone.services.TweetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "tweets")
public class TweetController {

    @Autowired
    private final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllTweets() {
        try {
            List<Tweet> tweets = tweetService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(tweets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Error al obtener todos los tweets.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneTweet(@PathVariable Long id) throws UserNotFoundException {
        Tweet tweet = tweetService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(tweet);
    }

    @PostMapping("")
    public ResponseEntity<?> saveTweet(@Valid @RequestBody Tweet tweet) {
        Tweet savedTweet = tweetService.save(tweet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTweet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTweet(@PathVariable Long id, @RequestBody Tweet tweet) {
        try {
            Tweet updatedTweet = tweetService.update(id, tweet);
            return ResponseEntity.status(HttpStatus.OK).body(updatedTweet);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error al actualizar el tweet con el ID proporcionado.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTweet(@PathVariable Long id) {
        try {
            boolean deleted = tweetService.delete(id);
            if (deleted) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"No se encontró ningún tweet con el ID proporcionado.\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Error al eliminar el tweet.\"}");
        }
    }
}

