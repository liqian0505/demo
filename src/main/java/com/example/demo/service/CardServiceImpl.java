package com.example.demo.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.example.demo.model.Card;
import com.example.demo.repository.CardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

	@Override
	public List<Card> findAllCards() {
		return cardRepository.findAll();
	}

	@Override
	public Card newCard(Card card) {
		return cardRepository.save(card);
	}

	@Override
	public Card updateCard(Integer id, Card newCard) {
        Card card = cardRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException());
        card.setContent(newCard.getContent());
        card.setJokeId(newCard.getJokeId());

		return cardRepository.save(card);
	}

	@Override
	public void deleteCard(Integer id) {
		cardRepository.deleteById(id);
	}

	@Override
	public List<Card> getCardByJokeid(Integer jokeId) {
		return cardRepository.findAllByJokeId(jokeId);
	}

}