package com.orka.taropro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.Toast;

import com.orka.taropro.domain.Card;
import com.orka.taropro.domain.CardDeck;
import com.orka.taropro.domain.CardReadingType;
import com.orka.utils.animation.DisplayNextView;
import com.orka.utils.animation.Flip3dAnimation;

public class CelticCrossActivityOld extends BaseActivity implements
		OnClickListener {
	private final static String TAG = CelticCrossActivity.class.getSimpleName();
	LinearLayout mainView;
	LinearLayout cardDeckLayout;
	TaroApplication taro;
	CardDeck celticCrossCards;
	Button buttonCardReading;
	Button buttonShuffle;
	private int maxSelectedCards = 10;
	private int currentSelectedCards;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		setContentView(R.layout.celticcross);
		super.onCreate(savedInstanceState);

		mainView = (LinearLayout) findViewById(R.id.layoutCelticCrossMain);
		buttonCardReading = (Button) findViewById(R.id.buttonCardReading);
		buttonCardReading.setOnClickListener(this);
		buttonShuffle = (Button) findViewById(R.id.buttonShuffle);
		buttonShuffle.setOnClickListener(this);

		taro = (TaroApplication) getApplication();

	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");

		// initialize card deck layout
		cardDeckLayout = (LinearLayout) findViewById(R.id.CardDeckLayout);

		// initialize card reading deck
		switch (taro.reading.getReadingType()) {
		case CELTIC_CROSS:
			break;
		default:
			taro.reading.clear();
			taro.reading.setReadingType(CardReadingType.CELTIC_CROSS);
			// Shuffle card deck
			taro.cardHelper.cardDeck.shuffle();

		}

		celticCrossCards = taro.reading.getCardDeck();
		maxSelectedCards = taro.reading.getNumOfCards();
		currentSelectedCards = celticCrossCards.getNumberOfCards();

		// initialize Reading place
		initReadingPlace();

		// initialize card deck

		if (currentSelectedCards < maxSelectedCards) {

			initCardDeck();

			// hide reading button
			buttonCardReading.setVisibility(View.INVISIBLE);
		} else {
			// show reading button
			buttonCardReading.setVisibility(View.VISIBLE);
		}

	}

	private void initCardDeck() {

		int resource;
		ImageView imageView;
		Display display = getWindowManager().getDefaultDisplay();
		int displayWidth = display.getWidth();
		int displayHeight = display.getHeight();
		int imageHeight;
		int imageWidth;
		int cardHeight;
		int cardWidth;
		LayoutParams lp;
		String cardName;

		for (int i = 0; i < taro.cardHelper.cardDeck.getNumberOfCards(); i++) {

			imageView = new ImageView(this);

			cardName = "card_";
			if (taro.cardHelper.cardDeck.isCardSelected(i)) {
				cardName += taro.cardHelper.cardDeck.getShuffledCard(i).getId();
				imageView.setVisibility(View.INVISIBLE);
			} else
				cardName += "back";

			resource = getResources().getIdentifier(cardName, "drawable",
					getPackageName());

			// Log.d(TAG, cardName + " resource id = " + resource);

			imageView.setImageResource(resource);

			imageHeight = imageView.getDrawable().getMinimumHeight();
			imageWidth = imageView.getDrawable().getMinimumWidth();

			cardHeight = Math
					.round((displayHeight > displayWidth ? displayHeight
							: displayWidth) / 7);
			cardWidth = Math.round(cardHeight * imageWidth / imageHeight);

			// Log.d(TAG, String.format(
			// "Display = %d x %d; Card = %d x %d; Image = %d x %d",
			// displayWidth, displayHeight, cardWidth, cardHeight,
			// imageWidth, imageHeight));

			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

			lp = new LinearLayout.LayoutParams(cardWidth, cardHeight, 0x11);

			imageView.setLayoutParams(lp);
			// imageView.onKeyDown(KeyEvent.KEYCODE_DPAD_CENTER, event)

			if (cardDeckLayout == null)
				Log.d(TAG, "cardDeckLayout is null");
			if (imageView == null)
				Log.d(TAG, "imageView is null");
			if (lp == null)
				Log.d(TAG, "lp is null");

			cardDeckLayout.addView(imageView);

			imageView.setTag(Integer.toString(i));
			imageView.setOnClickListener(this);
		}

		Log.d(TAG, String.format(
				"Number of childern is %d; ImageView in 3d position is %d",
				cardDeckLayout.getChildCount(),
				((ImageView) cardDeckLayout.getChildAt(3)).getId()));
	}

	private void initReadingPlace() {

		ImageView imageView;
		Display display = getWindowManager().getDefaultDisplay();
		int displayWidth = display.getWidth();
		int displayHeight = display.getHeight();
		int imageHeight;
		int imageWidth;
		int cardHeight;
		int cardWidth;
		LayoutParams lp = null;

		// hide reading cards
		for (int i = 1; i <= maxSelectedCards; i++) {

			imageView = (ImageView) findViewById(getResources().getIdentifier(
					"ImageViewCard" + i, "id", getPackageName()));
			// Derive LayoutParams for first and apply for all
			if (i == 1) {

				imageHeight = imageView.getDrawable().getMinimumHeight();
				imageWidth = imageView.getDrawable().getMinimumWidth();

				cardHeight = Math
						.round((displayHeight > displayWidth ? displayHeight
								: displayWidth) / 7);
				cardWidth = Math.round(cardHeight * imageWidth / imageHeight);
				lp = new TableRow.LayoutParams(cardWidth, cardHeight, 0x11);
			}

			imageView.setLayoutParams(lp);
			Card card = celticCrossCards.getCard(i - 1);
			if (card == null) {
				imageView.setVisibility(View.INVISIBLE);
			} else {
				imageView.setImageResource(getResources().getIdentifier(
						"card_" + card.getId(), "drawable", getPackageName()));
				imageView.setVisibility(View.VISIBLE);
			}
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
		// taro.cardHelper.cardDeck.clear();
	}

	public void reload() {
		Intent intent = getIntent();
		finish();
		startActivity(intent);
	}

	@Override
	public void onClick(View view) {

		if (view.equals(buttonCardReading)) {
			// button card reading click
			Log.d(TAG, "button card reading click");
			startActivity(new Intent(this, ReadingActivity.class));
			return;
		} else if (view.equals(buttonShuffle)) {
			// button shuffle click
			Log.d(TAG, "button shuffle click");
			taro.reading.clear();
			mainView.invalidate();
			reload();
			return;
		}

		Log.d(TAG,
				String.format("onClick on %s Child", view.getTag().toString()));

		int cardIndex = Integer.parseInt(view.getTag().toString());
		String cardName;

		if (taro.cardHelper.cardDeck.isCardSelected(cardIndex))
			Log.d(TAG, "onClick: The card already selected");
		else if (currentSelectedCards < maxSelectedCards) {

			// Add card into reading deck
			celticCrossCards.addCard(currentSelectedCards,
					taro.cardHelper.cardDeck.getShuffledCard(cardIndex));

			// Increase number of selected cards
			currentSelectedCards++;
			// Find ImageView on the reading field
			ImageView imageView = (ImageView) findViewById(getResources()
					.getIdentifier("ImageViewCard" + currentSelectedCards,
							"id", getPackageName()));
			// TODO : Register onClick listener for the ImageView
			// imageView.setOnClickListener(cardReading);
			// Get image resource name
			cardName = "card_"
					+ taro.cardHelper.cardDeck.getShuffledCard(cardIndex)
							.getId();
			// find resource by name
			int resource = getResources().getIdentifier(cardName, "drawable",
					getPackageName());

			Log.d(TAG,
					String.format("%s  resource id = %x", cardName, resource));
			// animate

			ImageView imageView2 = new ImageView(this);
			imageView2.setImageResource(resource);
			// make touched card invisible
			((ImageView) view).setVisibility(View.INVISIBLE);
			// make reading card visible
			imageView.setVisibility(View.VISIBLE);

			applyRotation(0, 90, imageView, imageView2);

			// populate image with new image
			// imageView.setImageResource(resource);

			// mark the card as selected
			taro.cardHelper.cardDeck.selectCard(cardIndex);
			Card card = taro.cardHelper.cardDeck.getShuffledCard(cardIndex);

			Toast toast = Toast.makeText(this, card.getArcana() + " Arcana: "
					+ card.getName() + (!card.isDirect() ? " (Reversed)" : ""),
					Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.BOTTOM, 0,
					(int) Math.round(imageView.getHeight() * 1.5));
			toast.show();

		}
		// remove card deck and start reading
		if (currentSelectedCards == maxSelectedCards) {
			// hide card deck
			((View) findViewById(R.id.horizontalScrollViewDeck))
					.setVisibility(View.GONE);
			// show button
			((Button) findViewById(R.id.buttonCardReading))
					.setVisibility(View.VISIBLE);

		}

	}

	private void applyRotation(float start, float end, ImageView image1,
			ImageView image2) {
		// Find the center of image
		final float centerX = image1.getWidth() / 2.0f;
		final float centerY = image1.getHeight() / 2.0f;

		// Create a new 3D rotation with the supplied parameter
		// The animation listener is used to trigger the next animation
		final Flip3dAnimation rotation = new Flip3dAnimation(start, end,
				centerX, centerY);
		rotation.setDuration(500);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView(image1, image2));

		image1.startAnimation(rotation);
	}
}
