package com.orka.taropro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TaroQuestionsActivity extends BaseActivity {
	
	public static final String TAG = TaroQuestionsActivity.class
			.getSimpleName();

	Button buttonQuickDecision;
	Button buttonExactQuestion;
	Button buttonUnderstandSituation;
	Button buttonDeepAnalysis;
	Button buttonLove;
	Button buttonAllSpreads;
	Button buttonSeeDeck;

	SpreadDescriptionDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		setContentView(R.layout.taroquestions);
		super.onCreate(savedInstanceState);

		dialog = new SpreadDescriptionDialog(this);

		// All Spread button
		buttonAllSpreads = (Button) findViewById(R.id.buttonAllSpreads);
		buttonAllSpreads.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(TaroQuestionsActivity.this,
						TaroActivity.class));

			}
		});

		// Deep Analysis button
		buttonDeepAnalysis = (Button) findViewById(R.id.buttonDeepAnalysis);
		buttonDeepAnalysis.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(taro,
				// R.string.dialog_description_celtic_cross,
				// Toast.LENGTH_LONG).show();
				dialog.showDialog(SpreadDescriptionDialog.DIALOG_CELTIC_CROSS);
				// startActivity(new Intent(TaroQuestionsActivity.this,
				// CelticCrossActivity.class));

			}
		});

		// Exact Question button
		buttonExactQuestion = (Button) findViewById(R.id.buttonQuestionExact);
		buttonExactQuestion.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(taro, R.string.dialog_description_horseshoe,
				// Toast.LENGTH_LONG).show();
				// startActivity(new Intent(TaroQuestionsActivity.this,
				// HorseshoeActivity.class));
				dialog.showDialog(SpreadDescriptionDialog.DIALOG_HORSESHOE);

			}
		});

		// Quick Decision button
		buttonQuickDecision = (Button) findViewById(R.id.buttonQuestionQuickDecision);
		buttonQuickDecision.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(taro, R.string.dialog_description_one_card,
				// Toast.LENGTH_LONG).show();
				// startActivity(new Intent(TaroQuestionsActivity.this,
				// OneCardActivity.class));
				dialog.showDialog(SpreadDescriptionDialog.DIALOG_ONE_CARD);
			}
		});

		// Understand Situation button
		buttonUnderstandSituation = (Button) findViewById(R.id.buttonUnderstandSituation);
		buttonUnderstandSituation
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// Toast.makeText(taro,
						// R.string.dialog_description_threecard,
						// Toast.LENGTH_LONG).show();
						// startActivity(new Intent(TaroQuestionsActivity.this,
						// ThreeCardActivity.class));
						dialog.showDialog(SpreadDescriptionDialog.DIALOG_THREE_CARS);

					}
				});

		// Understand Situation button
		buttonLove = (Button) findViewById(R.id.buttonLove);
		buttonLove.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(taro, R.string.dialog_description_mirror,
				// Toast.LENGTH_LONG).show();
				// startActivity(new Intent(TaroQuestionsActivity.this,
				// MirrorActivity.class));
				dialog.showDialog(SpreadDescriptionDialog.DIALOG_MIRROR);

			}
		});
		// Browse the Tarot Deck
				buttonSeeDeck = (Button) findViewById(R.id.buttonSeeTheDeck);
				buttonSeeDeck.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// Toast.makeText(taro, R.string.dialog_description_mirror,
						// Toast.LENGTH_LONG).show();
						 startActivity(new Intent(TaroQuestionsActivity.this,
						 DeckBrowseActivity.class));
						

					}
				});
	}

}
