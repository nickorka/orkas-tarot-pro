package com.orka.taropro;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.orka.taropro.domain.CardReadingType;

public class OneCardActivity extends BaseSpreadActivity {
	private final static String TAG = OneCardActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		setContentView(R.layout.onecard);
		mainView = (LinearLayout) findViewById(R.id.layoutOneCardMain);
		numberOfRows = 2;
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {

		Log.d(TAG, "onResume");

		// initialize card reading deck
		switch (taro.reading.getReadingType()) {
		case ONECARD:
			break;
		default:
			taro.reading.clear();
			taro.reading.setReadingType(CardReadingType.ONECARD);
			// Shuffle card deck
			taro.cardHelper.cardDeck.shuffle();

		}

		super.onResume();

	}

}
