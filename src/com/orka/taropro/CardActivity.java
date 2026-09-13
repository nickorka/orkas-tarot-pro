package com.orka.taropro;

import com.orka.taropro.domain.Card;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

public class CardActivity extends BaseActivity {

	private Card card = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.card);
		super.onCreate(savedInstanceState);
		
		card = taro.getCurrentCard();
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		if(card != null) {
			ImageView image = (ImageView) findViewById(R.id.imageViewCard);
			
			if (image != null) {
				String cardName = String.format("card_%02d", card.getCardId());
				image.setImageResource(getResources().getIdentifier(
						cardName, "drawable", getPackageName()));
			}
			
			TextView textView = (TextView) findViewById(R.id.textViewCardName);
			textView.setText(Html.fromHtml("<b><u>"
					+ card.getName()
					+ "</u></b>"));
			
			textView = (TextView) findViewById(R.id.textViewCardMeaning);
			textView.setText(Html.fromHtml("<b><u>"
					+ "Card Meaning:<br>"
					+ "</u></b>"
					+ card.getMeaning()));
			
			textView = (TextView) findViewById(R.id.textViewUprightReading);
			card.setDirect(true);
			textView.setText(Html.fromHtml("<b><u>"
					+ "Upright Reading:<br>"
					+ "</u></b>"
					+ card.getReading()));
			
			textView = (TextView) findViewById(R.id.textViewReverseReading);
			card.setDirect(false);
			textView.setText(Html.fromHtml("<b><u>"
					+ "Reverse Reading:<br>"
					+ "</u></b>"
					+ card.getReading()));
			
		}
		
	}

}
