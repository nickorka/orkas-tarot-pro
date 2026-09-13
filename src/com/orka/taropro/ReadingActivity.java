package com.orka.taropro;

import java.io.ByteArrayOutputStream;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.orka.taropro.domain.Card;

public class ReadingActivity extends BaseActivity {

	private static final String TAG = ReadingActivity.class.getSimpleName();

	private static final int DIALOG_REVIEW = 5;

	ListView readingList;
	private CardAdapter adapter;
	TaroApplication taro;
	int cardHight;
	int cardWidth;

	private String[] readings = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");

		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
		// WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

		setContentView(R.layout.reading);

		taro = (TaroApplication) getApplication();
		readingList = (ListView) findViewById(R.id.listViewReading);

		Card[] cards = taro.reading.getCardDeck().getCardsArray();

		adapter = new CardAdapter(this, R.layout.reading_row, cards);
		readingList.setAdapter(adapter);

		// increment number of reading for review rating tracking system
		taro.rating.incrementReading();

		super.onCreate(savedInstanceState);

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_REVIEW:
			return new AlertDialog.Builder(ReadingActivity.this)
					.setIcon(R.drawable.taro)
					.setTitle(R.string.dialog_title_review)
					.setMessage(R.string.dialog_message_review)
					.setPositiveButton(R.string.dialog_review_button,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Log.d(TAG, "Review button has been pressed");
									// mark that review has been made
									taro.rating.setMadeReview(true);
									// redirect to android market
									Intent intent = new Intent(
											Intent.ACTION_VIEW);
									intent.setData(Uri
											.parse("market://details?id=com.orka.taropro"));
									startActivity(intent);

								}
							})
					.setNeutralButton(R.string.dialog_latter_button,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Log.d(TAG, "Later button has been pressed");
									taro.rating.incrementLatterClick();

								}
							})
					.setNegativeButton(R.string.dialog_dont_review_button,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Log.d(TAG,
											"Don't ask button has been pressed");
									taro.rating.setDontWantToReview(true);

								}
							}).create();
		}
		return null;
	}

	@Override
	protected void onResume() {
		// check for rating review time
		if (taro.rating.getNumberOfReadings() > RatingHelper.MAX_READINGS_BEFORE_REVIEW_REQUEST) {
			if (!(taro.rating.isDontWantToReview() || taro.rating
					.isMadeReview())) {
				Log.d(TAG, "It's time for rating review dialog");
				showDialog(DIALOG_REVIEW);
			}
		}
		super.onResume();
	}

	private class CardAdapter extends ArrayAdapter<Card> {

		Bitmap bitmap;
		byte[] byteArray;

		public CardAdapter(Context context, int textViewResourceId, Card[] cards) {
			super(context, textViewResourceId, cards);
			readings = new String[taro.reading.getReadingType().getNumOfCards()];
		}

		@Override
		public View getView(int position, View cardRowView, ViewGroup parent) {
			View view = cardRowView;
			String[] meanings = null;

			if (view == null) {
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.reading_row, null);
			}

			Card card = taro.reading.getCardDeck().getCard(position);
			String cardName = "card_back";
			if (card != null) {
				Log.d(TAG, String.format("Card id = %d", card.getCardId()));
				// setup image
				ImageView image = (ImageView) view
						.findViewById(R.id.imageViewReadingCard);
				if (image != null) {
					cardName = String.format("card_%02d", card.getCardId())
							+ (card.isDirect() ? "" : "_2");
					image.setImageResource(getResources().getIdentifier(
							cardName, "drawable", getPackageName()));
				}

				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 8;

				bitmap = BitmapFactory.decodeResource(
						getResources(),
						getResources().getIdentifier(cardName, "drawable",
								getPackageName()), options); // (cardName +
																// ".jpg");

				// bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();

				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
				byteArray = stream.toByteArray();

				readings[position] = String.format(
						"<img src=\"data:image/jpeg;base64,%s\">",
						Base64.encodeToString(byteArray, Base64.NO_WRAP));
				 Log.d(TAG, String.format("image string %s",
				 readings[position]));

				// setup card reading meaning
				TextView readingMeaning = (TextView) view
						.findViewById(R.id.textViewReadinMeaning);

				switch (taro.reading.getReadingType()) {
				case CELTIC_CROSS:
					meanings = getResources().getStringArray(
							R.array.stringArrayCelticCross);
					break;
				case ONECARD:
					meanings = getResources().getStringArray(
							R.array.stringArrayOneCard);
					break;
				case HORSESHOE:
					meanings = getResources().getStringArray(
							R.array.stringArrayHorseshoe);
					break;
				case THREECARD:
					meanings = getResources().getStringArray(
							R.array.stringArrayThreeCard);
					break;
				case MIRROR:
					meanings = getResources().getStringArray(
							R.array.stringArrayMirror);
					break;

				}

				readingMeaning
						.setText(Html.fromHtml("<b>"
								+ meanings[position].split("\n")[0]
								+ "</b>"
								+ (meanings[position].split("\n").length == 1 ? ""
										: "<br />"
												+ meanings[position]
														.split("\n")[1])));
				readings[position] += Html.toHtml((Spanned) readingMeaning
						.getText());
				// setup card meaning
				TextView cardMeaning = (TextView) view
						.findViewById(R.id.textViewCardMeaning);
				cardMeaning.setText(Html.fromHtml("<b>"
						+ taro.reading.getCardDeck().getCard(position)
								.getName()
						+ "</b><br>"
						+ taro.reading.getCardDeck().getCard(position)
								.getMeaning()));
				readings[position] += Html.toHtml((Spanned) cardMeaning
						.getText());
				// setup reading
				TextView cardReading = (TextView) view
						.findViewById(R.id.textViewCardReading);
				cardReading.setText(Html.fromHtml("<b>"
						+ (taro.reading.getCardDeck().getCard(position)
								.isDirect() ? "Upright" : "Reverse")
						+ " reading"
						+ "</b><br>"
						+ taro.reading.getCardDeck().getCard(position)
								.getReading()));

				readings[position] += Html.toHtml((Spanned) cardReading
						.getText());
			}

			return view;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.readingmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.itemShare:
			shareReading();
			break;
		}

		return true;
	}

	private String getReading() {
		String reading = "<html><body>";

		

		for (int i = 0; i < taro.reading.getReadingType().getNumOfCards(); i++  ) {
			reading += readings[i];
		}
		
		reading += "</body></html>";

		return reading;

	}

	private void shareReading() {
		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("text/html");

		share.putExtra(Intent.EXTRA_SUBJECT, "Tarot Reading");
		share.putExtra(Intent.EXTRA_TEXT, getReading());
		
		

		startActivity(Intent.createChooser(share, "Share Reading"));
	}
}
