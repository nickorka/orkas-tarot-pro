package com.orka.taropro;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.orka.taropro.domain.CardReadingType;

public class HorseshoeActivity extends BaseSpreadActivity {
	private final static String TAG = HorseshoeActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		setContentView(R.layout.horseshoe);
		mainView = (LinearLayout) findViewById(R.id.layoutMain);
		numberOfRows = 7;
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {

		Log.d(TAG, "onResume");

		// initialize card reading deck
		switch (taro.reading.getReadingType()) {
		case HORSESHOE:
			break;
		default:
			taro.reading.clear();
			taro.reading.setReadingType(CardReadingType.HORSESHOE);
			// Shuffle card deck
			taro.cardHelper.cardDeck.shuffle();

		}

		super.onResume();

	}

}
