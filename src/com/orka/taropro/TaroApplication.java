package com.orka.taropro;

import com.orka.taropro.domain.Card;
import com.orka.taropro.domain.CardReading;

import android.app.Application;

public class TaroApplication extends Application {

	CardHelper cardHelper;
	CardReading reading;
	RatingHelper rating;
	boolean isFirstActivity = true;
	private Card	currentCard;

	@Override
	public void onCreate() {
		super.onCreate();

		// initialize card helper
		cardHelper = new CardHelper();
		cardHelper.populateCardDeck(this);
		// initialize card reading
		reading = new CardReading();
		// initialize review rating helper
		rating = new RatingHelper(this);
		// rating.savePreferences(); // this for debug only
		rating.loadPreferences();
		// initiate first ad provider
	}

	@Override
	public void onTerminate() {
		// save review ratings
		rating.savePreferences();
		super.onTerminate();
	}

	public Card getCurrentCard() {
		return currentCard;
	}

	public void setCurrentCard(Card currentCard) {
		this.currentCard = currentCard;
	}

	
}
