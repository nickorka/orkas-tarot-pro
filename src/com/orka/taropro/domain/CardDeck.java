package com.orka.taropro.domain;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * CardDeck class stores all cards and shuffle them as one deck.
 * 
 * @author nborunov
 * 
 */
public class CardDeck {

	private HashMap<Integer, Card> cards;
	private HashMap<Integer, Boolean> selectedCard;
	private HashMap<Integer, Integer> shuffledDeck;

	/**
	 * CardDeck constructor
	 */
	public CardDeck() {
		cards = new HashMap<Integer, Card>();
		shuffledDeck = new HashMap<Integer, Integer>();
		selectedCard = new HashMap<Integer, Boolean>();
	}

	/**
	 * Clears related maps
	 */
	public void clear() {
		shuffledDeck.clear();
		selectedCard.clear();
		cards.clear();
	}

	/**
	 * Adds a new card to a deck as not selected
	 * 
	 * @param key
	 *            - position of the card
	 * @param card
	 *            - a Card class
	 */
	public void addCard(int key, Card card) {
		cards.put(key, card);
		selectedCard.put(key, false);
	}

	/**
	 * Adds a new card to a deck as selected
	 * 
	 * @param key
	 *            - position of the card
	 * @param card
	 *            - a Card class
	 */
	public void addSelectedCard(int key, Card card) {
		cards.put(key, card);
		selectedCard.put(key, true);
	}

	/**
	 * Returns a card in a key position for not shuffled deck
	 * 
	 * @param key
	 *            - a position
	 * @return - a card
	 */
	public Card getCard(int key) {
		if (key < 0 || key >= cards.size())
			return null;
		return cards.get(key);
	}

	/**
	 * Returns a card in a key position for shuffled deck
	 * 
	 * @param key
	 *            - a position
	 * @return - a card
	 */
	public Card getShuffledCard(int key) {
		if (key < 0 || key >= shuffledDeck.size())
			return null;
		return cards.get(shuffledDeck.get(key));
	}

	/**
	 * Shuffles all cards as one deck.
	 */
	public void shuffle() {

		int cardNum = cards.size();
		int nextCardKey;
		Random cardKeyGenerator = new Random();
		Random cardDirectionGenerator = new Random();
		shuffledDeck.clear();
		shuffledDeck = new HashMap<Integer, Integer>();

		for (int i = 0; i < cardNum; i++) {

			// generate next key
			nextCardKey = cardKeyGenerator.nextInt(cardNum);
			// check if the card was not already stored in the shuffled deck
			while (shuffledDeck.containsValue(nextCardKey)) {
				if (nextCardKey == cardNum - 1)
					nextCardKey = 0;
				else
					nextCardKey++;
			}
			// derive card direction
			cards.get(nextCardKey).setDirect(
					cardDirectionGenerator.nextBoolean());
			// add new card to the deck
			shuffledDeck.put(i, nextCardKey);
			// mark all cards as unselected
			selectedCard.put(i, false);
		}
	}

	/**
	 * Returns number of cards in a deck
	 * 
	 * @return - a number of cards
	 */
	public int getNumberOfCards() {
		return cards.size();
	}

	/**
	 * Return number of shuffled cards
	 * 
	 * @return - a number of cards
	 */
	public int getNumberOfShuffledCards() {
		return shuffledDeck.size();
	}

	public boolean isCardSelected(int key) {
		return selectedCard.get(shuffledDeck.get(key));
	}

	public void selectCard(int key) {
		selectedCard.put(shuffledDeck.get(key), true);
	}

	public Card[] getCardsArray() {

		Card[] cardArray = new Card[cards.size()];

		for (int i = 0; i < cards.size(); i++) {
			cardArray[i] = cards.get(i);
		}

		return cardArray;

	}

	public List<Card> getCardsArray(CardType type) {

		List<Card> cardArray = new ArrayList<Card>();

		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getCardType() == type) {
				cardArray.add(cards.get(i));
			}
		}

		return cardArray;

	}

}
