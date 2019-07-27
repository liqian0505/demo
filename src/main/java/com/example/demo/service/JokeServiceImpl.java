package com.example.demo.service;

import com.example.demo.exception.JokeNotFoundException;
import com.example.demo.model.Joke;
import com.example.demo.repository.JokeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
public class JokeServiceImpl implements JokeService {

    @Autowired
    private JokeRepository jokeRepository;

    @Override
    public List<Joke> findAllJokes() {
        return jokeRepository.findAll();
    }

    @Override
    public Joke newJoke(Joke joke) {
        return jokeRepository.save(joke);
    }

    @Override
    public Joke updateJoke(Integer id, Joke newJoke) {
        jokeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        
        return jokeRepository.save(newJoke);
    }

    @Override
    public void deleteJoke(Integer id) {
        jokeRepository.deleteById(id);
    }

    @Override
    public Joke findJokeById(Integer id) throws JokeNotFoundException{
        // if (jokeRepository.findById(id).isPresent()) {
        //     return jokeRepository.findById(id).get();
        // } else {
        //     throw new JokeNotFoundException("joke can not found in JokeRepository");
        // }
        return jokeRepository.findById(id)
            .orElseThrow(()-> new JokeNotFoundException("joke can not found"));
    }
}
