package com.example.demo.repository;




import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.example.demo.model.Joke;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JokeRepositoryTests {
    @Autowired
    JokeRepository jokeRepository;

    Joke joke1,joke2;

    @Before
    public void setUp(){
        this.jokeRepository.deleteAll();
        joke1 = new Joke();
        joke1.setTitle("abc");
        joke1.setContent("content1");
        jokeRepository.save(joke1);
        joke2 = new Joke();
        joke2.setContent("content2");
        joke2.setTitle("title2");
        jokeRepository.save(joke2);
    }

    @Test
    public void getJokeList_Then_ReturnJokeList(){
        List<Joke> jokeList = jokeRepository.findAll();
        assertThat(jokeList.size()).isEqualTo(2);
        Joke joke = jokeList.get(1);
        assertThat(joke).extracting("content").contains("content");
    }

    @After
    public void cleanJokes(){
        jokeRepository.deleteAll();
    }
}