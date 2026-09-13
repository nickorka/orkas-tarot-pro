package com.orka.taropro;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.orka.taropro.domain.CardReadingType;

public class MirrorActivity extends BaseSpreadActivity {
	private final static String TAG = MirrorActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		setContentView(R.layout.mirror);
		mainView = (LinearLayout) findViewById(R.id.layoutMain);
		numberOfRows = 11;
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {

		Log.d(TAG, "onResume");

		// initialize card reading deck
		switch (taro.reading.getReadingType()) {
		case MIRROR:
			break;
		default:
			taro.reading.clear();
			taro.reading.setReadingType(CardReadingType.MIRROR);
			// Shuffle card deck
			taro.cardHelper.cardDeck.shuffle();

		}

		super.onResume();

	}

}
