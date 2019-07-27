package com.example.demo.repository;


import java.util.List;

import com.example.demo.model.Card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    public List<Card> findAllByJokeId(Integer jokeId);
}
