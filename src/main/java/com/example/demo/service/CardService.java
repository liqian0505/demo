package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Card;


public interface CardService {


    public List<Card> findAllCards();

    public Card newCard(Card card);

    public Card updateCard(Integer id, Card newCard);

    public void deleteCard(Integer id);

    public List<Card> getCardByJokeid(Integer jokeId);
}