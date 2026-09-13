package com.orka.taropro;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * The class helps to handle rating reviews. It stores shared preferences in
 * private file for an application.
 * 
 * @author nborunov
 * 
 */
public class RatingHelper {
	private static final String PREF_FILE_NAME = "appreview";
	private final static String NUM_OF_READINGS = "NuberOfReadings";
	private final static String NUM_OF_LATTER_CLICKS = "NumberOfLatterClicks";
	private final static String MADE_REVIEW = "MadeReview";
	private final static String DONT_WANT_TO_REVIEW = "DontWantToReview";
	
	public final static int MAX_READINGS_BEFORE_REVIEW_REQUEST = 20;

	private long numberOfReadings = 0;
	private int numberOfLatterClicks = 0;
	private boolean madeReview = false;
	private boolean dontWantToReview = false;
	private Context context;

	public RatingHelper(Context context) {
		this.context = context;
	}

	public long getNumberOfReadings() {
		return numberOfReadings;
	}

	public void setNumberOfReadings(long numberOfReadings) {
		this.numberOfReadings = numberOfReadings;
	}

	public int getNumberOfLatterClicks() {
		return numberOfLatterClicks;
	}

	public void setNumberOfLatterClicks(int numberOfLatterClicks) {
		this.numberOfLatterClicks = numberOfLatterClicks;
	}

	public boolean isMadeReview() {
		return madeReview;
	}

	public void setMadeReview(boolean madeReview) {
		this.madeReview = madeReview;
	}

	public boolean isDontWantToReview() {
		return dontWantToReview;
	}

	public void setDontWantToReview(boolean dontWantToReview) {
		this.dontWantToReview = dontWantToReview;
	}

	public void savePreferences() {

		SharedPreferences sharedPrefs = context.getSharedPreferences(
				PREF_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = sharedPrefs.edit();

		editor.clear();
		editor.putLong(NUM_OF_READINGS, numberOfReadings);
		editor.putInt(NUM_OF_LATTER_CLICKS, numberOfLatterClicks);
		editor.putBoolean(MADE_REVIEW, madeReview);
		editor.putBoolean(DONT_WANT_TO_REVIEW, dontWantToReview);
		editor.commit();

	}

	public void loadPreferences() {

		SharedPreferences sharedPrefs = context.getSharedPreferences(
				PREF_FILE_NAME, Context.MODE_PRIVATE);

		numberOfReadings = sharedPrefs.getLong(NUM_OF_READINGS,
				numberOfReadings);
		numberOfLatterClicks = sharedPrefs.getInt(NUM_OF_LATTER_CLICKS,
				numberOfLatterClicks);
		madeReview = sharedPrefs.getBoolean(MADE_REVIEW, madeReview);
		dontWantToReview = sharedPrefs.getBoolean(DONT_WANT_TO_REVIEW,
				dontWantToReview);
	}

	public void incrementReading() {
		numberOfReadings++;
	}

	public void incrementLatterClick() {
		numberOfLatterClicks++;
		// if more than 2 postponed reviews we are going to stop asking for a review
		if (numberOfLatterClicks > 2)
			dontWantToReview = true;
		// reset number of readings
		numberOfReadings = 0;
	}

}
