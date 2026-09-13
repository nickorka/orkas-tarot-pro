package com.orka.taropro.domain;

import java.util.HashMap;

public class CardReading {

	private CardReadingType readingType;
	private CardDeck cardDeck;
	
	public CardReading() {
		readingType = CardReadingType.UNKNOWN;
		cardDeck = new CardDeck();
	}

	public CardReadingType getReadingType() {
		return readingType;
	}

	public void setReadingType(CardReadingType readingType) {
		this.readingType = readingType;
	}

	public CardDeck getCardDeck() {
		return cardDeck;
	}

	public void setCardDeck(CardDeck cardDeck) {
		this.cardDeck = cardDeck;
	}

	public int getNumOfCards() {
		return readingType.getNumOfCards();
	}

	public HashMap<Integer, String> getCardMeaning() {
		return readingType.getMeaning();
	}

	public void clear() {
		readingType = CardReadingType.UNKNOWN;
		if (cardDeck != null)
			cardDeck.clear();
	}

}
