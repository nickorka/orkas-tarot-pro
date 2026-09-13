package com.orka.taropro;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.orka.taropro.domain.CardReadingType;

public class ThreeCardActivity extends BaseSpreadActivity {
	private final static String TAG = ThreeCardActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		setContentView(R.layout.threecard);
		mainView = (LinearLayout) findViewById(R.id.layoutThreeCardMain);
		numberOfRows = 2;
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {

		Log.d(TAG, "onResume");

		// initialize card reading deck
		switch (taro.reading.getReadingType()) {
		case THREECARD:
			break;
		default:
			taro.reading.clear();
			taro.reading.setReadingType(CardReadingType.THREECARD);
			// Shuffle card deck
			taro.cardHelper.cardDeck.shuffle();

		}

		super.onResume();

	}

}
