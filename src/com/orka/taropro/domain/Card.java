package com.orka.taropro.domain;

public class Card {
	public static final String reverseIdSuffix = "_2";

	private int cardId;
	private String arcana;
	private String suit;
	private String name;
	private String directReading;
	private String reverseReading;
	private String meaning;
	private String directId;
	private boolean isDirect = true;
	private CardType cardType = CardType.UNKNOWN;
	

	public CardType getCardType() {
		return cardType;
	}

	public String getArcana() {
		return arcana;
	}

	public void setArcana(String arcana) {
		this.arcana = arcana;
		cardType = cardType.getByName(arcana);
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
		if(suit != null && suit.length() > 0) {
			cardType = cardType.getByName(suit);
		}
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public void setId(int cardId) {
		this.setCardId(cardId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDirectReading(String directReading) {
		this.directReading = directReading;
	}

	public String getReading() {
		return isDirect ? directReading : reverseReading;
	}

	public void setReverseReading(String reverseReading) {
		this.reverseReading = reverseReading;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getId() {
		return isDirect ? directId : directId + reverseIdSuffix;
	}

	public void setDirectId(String directId) {
		this.directId = directId;
	}

	public boolean isDirect() {
		return isDirect;
	}

	public void setDirect(boolean isDirect) {
		this.isDirect = isDirect;
	}

}
