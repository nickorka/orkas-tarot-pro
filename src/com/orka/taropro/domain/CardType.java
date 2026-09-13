package com.orka.taropro.domain;

public enum CardType {
	MAJOR, WANDS, CAPS, SWORDS, PENTACLES, UNKNOWN;
	
	public CardType getByName(String name) {
		for(CardType type : CardType.values()) {
			if(type.name().equals(name.toUpperCase())) {
				return type;
			}
		}
		
		return UNKNOWN;
	}
	
	public boolean compareWithString(String name) {
		return this.name().equals(name.toUpperCase());
	}
}
