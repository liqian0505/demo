package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Joke;
import com.example.demo.repository.JokeRepository;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JokeServiceTests {

    @Autowired
    JokeService jokeService;

    @MockBean
    JokeRepository jokeRepository;
    
    @Before
    public void setUp(){
        Joke joke = new Joke();
        joke.setId(111);
        joke.setContent("content1");
        joke.setTitle("title1");
        Joke joke1 = new Joke();
        joke1.setId(222);
        joke1.setTitle("title2");
        joke1.setContent("content2");

        List<Joke> allJokes = Arrays.asList(joke,joke1);

        Mockito.when(jokeRepository.findById(joke.getId())).thenReturn(Optional.of(joke));
        Mockito.when(jokeRepository.findById(joke1.getId())).thenReturn(Optional.of(joke1));
        Mockito.when(jokeRepository.findById(0)).thenReturn(null);
        Mockito.when(jokeRepository.save(new Joke())).thenReturn(joke1);
        Mockito.when(jokeRepository.findAll()).thenReturn(allJokes);
    }

    @Test
    public void given2Jokes_when_getAllJokes_thenReturn2Records(){
        List<Joke> allJokes = jokeService.findAllJokes();
        assertThat(allJokes).hasSize(2).extracting(Joke::getTitle).contains("title1", "title2");
    }

}