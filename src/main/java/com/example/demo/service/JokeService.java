package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.JokeNotFoundException;
import com.example.demo.model.Joke;

public interface JokeService {
    
    List<Joke> findAllJokes();

    Joke newJoke(Joke joke);

    Joke updateJoke(Integer id, Joke joke);

    void deleteJoke(Integer id);

    Joke findJokeById(Integer id) throws JokeNotFoundException;
} 