package com.orka.taropro;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {
	@SuppressWarnings("unused")
	private static final String TAG = BaseActivity.class.getSimpleName();


	TaroApplication taro;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		taro = (TaroApplication) getApplication();

	}

	@Override
	protected void onDestroy() {

		// save preferences
		taro.rating.savePreferences();

		super.onDestroy();
	}

}
