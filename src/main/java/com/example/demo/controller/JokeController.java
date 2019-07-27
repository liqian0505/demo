package com.example.demo.controller;

import java.util.List;

import com.example.demo.exception.JokeNotFoundException;
import com.example.demo.model.Joke;
import com.example.demo.service.JokeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class JokeController {
    @Autowired
    private JokeService jokeService;
    
    @GetMapping(value = "/api/jokes")
    public List<Joke> getAllJokes() {
        List<Joke> jokes = jokeService.findAllJokes();
        return jokes;
    }

    @PostMapping(value = "/api/jokes")
    public Joke newJoke(@RequestBody Joke joke) {
        return jokeService.newJoke(joke);
    }

    @PutMapping(value = "/api/jokes/{id}")
    public Joke updateJoke(@PathVariable(name = "id") Integer id, @RequestBody Joke newJoke) {
        return jokeService.updateJoke(id, newJoke);
    }

    @GetMapping(value = "/api/err")
    public String run(){
        throw new RuntimeException("cvicse");
    }

    @GetMapping(value = "/api/jokes/")
    public Joke findJokeById(Integer id){
        try {
            return jokeService.findJokeById(id);
        } catch (JokeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Joke not found", e);
        }
    }
    
}