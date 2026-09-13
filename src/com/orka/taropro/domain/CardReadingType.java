package com.orka.taropro.domain;

import java.util.HashMap;

public enum CardReadingType {
	CELTIC_CROSS(1), HORSESHOE(2), ONECARD(3), THREECARD(4), MIRROR(5), UNKNOWN(0);

	private final int index;
	private final int numOfCards;
	private final HashMap<Integer, String> meaning;

	CardReadingType(int index) {
		this.index = index;
		// populate meaning
		meaning = new HashMap<Integer, String>();

		switch (index) {
		case 1:
			numOfCards = 10;
			setCelticCrossMeaning();
			break;
		case 2:
			numOfCards = 7;
			setHorseshoeMeaning();
			break;
		case 3:
			numOfCards = 1;
			setOneCardMeaning();
			break;
		case 4:
			numOfCards = 3;
			break;
		case 5:
			numOfCards = 14;
			break;
		default:
			numOfCards = 0;
		}
	}

	public int getIndex() {
		return index;
	}

	public int getNumOfCards() {
		return numOfCards;
	}

	public HashMap<Integer, String> getMeaning() {
		return meaning;
	}
	
	public String getMeaning(int key) {
		return meaning.get(key);
	}

	public CardReadingType getByIndex(int index) {
		for (CardReadingType type : CardReadingType.values()) {
			if (index == type.getIndex())
				return type;
		}
		return CardReadingType.UNKNOWN;
	}

	private void setCelticCrossMeaning() {
		for (int i = 0; i < numOfCards; i++) {
			switch (i) {
			case 0:
				meaning.put(i, "Reading Card Meaning " + i);
				break;
			case 1:
				meaning.put(i, "Reading Card Meaning " + i);
				break;
			case 2:
				meaning.put(i, "Reading Card Meaning " + i);
				break;
			case 3:
				meaning.put(i, "Reading Card Meaning " + i);
				break;
			case 4:
				meaning.put(i, "Reading Card Meaning " + i);
				break;
			case 5:
				meaning.put(i, "Reading Card Meaning " + i);
				break;
			case 6:
				meaning.put(i, "Reading Card Meaning " + i);
				break;
			case 7:
				meaning.put(i, "Reading Card Meaning " + i);
				break;
			case 8:
				meaning.put(i, "Reading Card Meaning " + i);
				break;
			case 9:
				meaning.put(i, "Reading Card Meaning " + i);
				break;
			}
		}
	}

	private void setHorseshoeMeaning() {
		for (int i = 0; i < numOfCards; i++) {
			switch (i) {
			case 0:
				meaning.put(i, "Reading Card Meaning " + i);
				break;
			case 1:
				meaning.put(i, "Reading Card Meaning " + i);
				break;
			case 2:
				meaning.put(i, "Reading Card Meaning " + i);
				break;
			}
		}
	}
	
	private void setOneCardMeaning() {
		for (int i = 0; i < numOfCards; i++) {
			switch (i) {
			case 0:
				meaning.put(i, "Reading Card Meaning " + i);
				break;
			}
		}		
	}
}
