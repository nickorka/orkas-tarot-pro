package com.orka.taropro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TaroActivity extends BaseActivity {
	/** Called when the activity is first created. */
	private final static String TAG = TaroActivity.class.getSimpleName();


	Button buttonCelticCross;
	Button buttonHorseshoeSpread;
	Button buttonOneCard;
	Button buttonThreeCard;
	Button buttonMirror;
	Button buttonTaroDeck;
	
	private SpreadDescriptionDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.taromain);
		super.onCreate(savedInstanceState);
		
		dialog = new SpreadDescriptionDialog(this);

		buttonCelticCross = (Button) findViewById(R.id.buttonCrossTaro);
		buttonOneCard = (Button) findViewById(R.id.buttonOneCard);
		buttonHorseshoeSpread = (Button) findViewById(R.id.buttonHorseshoe);
		buttonThreeCard = (Button) findViewById(R.id.buttonThreeCard);
		buttonMirror = (Button) findViewById(R.id.buttonMirror);
		buttonTaroDeck = (Button) findViewById(R.id.buttonTaroDeck);

		buttonCelticCross.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "buttonCelticCross:onClick");
				dialog.showDialog(SpreadDescriptionDialog.DIALOG_CELTIC_CROSS);
			}
		});
		buttonOneCard.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "buttonOneCard:onClick");
				dialog.showDialog(SpreadDescriptionDialog.DIALOG_ONE_CARD);
			}
		});

		buttonHorseshoeSpread.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "buttonHorseshoeSpread:onClick");
				dialog.showDialog(SpreadDescriptionDialog.DIALOG_HORSESHOE);
			}
		});
		
		buttonThreeCard.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "buttonHorseshoeSpread:onClick");
				dialog.showDialog(SpreadDescriptionDialog.DIALOG_THREE_CARS);
			}
		});
		
		buttonMirror.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "buttonMirror:onClick");
				dialog.showDialog(SpreadDescriptionDialog.DIALOG_MIRROR);
			}
		});
		
		buttonTaroDeck.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TaroActivity.this.startActivity(new Intent(taro, DeckBrowseActivity.class));
				
			}
		});
	}

}