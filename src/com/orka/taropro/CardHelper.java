package com.orka.taropro;

import java.io.IOException;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.util.Log;

import com.orka.taropro.domain.Card;
import com.orka.taropro.domain.CardDeck;

public class CardHelper {
	public CardDeck cardDeck = new CardDeck();
	private static final String TAG = CardHelper.class.getSimpleName();

	/**
	 * Pull data for cards from external source
	 */
	public void populateCardDeck(Context context) {

		// Let's parse taro cards information from xml
		XmlParser parser = new XmlParser();
		parser.parse(context);
		HashMap<Integer, Card> cards = parser.getXML();

		// TODO it's a mockup for now
		for (int i = 0; i < cards.size(); i++) {

			//Log.d(TAG, "Card " + i + ":" + cards.get(i).toString());

			cardDeck.addCard(i, cards.get(i));
		}

	}

	class XmlParser {

		XmlPullParser myXml;

		void parse(Context context) {

			try {
				XmlPullParserFactory factory = XmlPullParserFactory
						.newInstance();
				factory.setValidating(false);
				myXml = factory.newPullParser();

				myXml.setInput(
						context.getResources().getAssets()
								.open("res/xml/tarots.xml"), null);

			} catch (IOException e) {
				Log.d(TAG, "XMLParser:parse: IOException");
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		HashMap<Integer, Card> getXML() {
			HashMap<Integer, Card> cards = new HashMap<Integer, Card>();
			Card card = null;
			String currentTag = null;
			String currentText;

			try {

				int eventType = myXml.getEventType();
				while (eventType != XmlPullParser.END_DOCUMENT) {
					if (eventType == XmlPullParser.START_DOCUMENT) {
//						Log.d(TAG, "XMLParser:getXML: Start Document");
					} else if (eventType == XmlPullParser.START_TAG) {
						// Define current tag
						currentTag = myXml.getName();
//						Log.d(TAG, "XMLParser:getXML: Start tag = "
//								+ currentTag);
					} else if (eventType == XmlPullParser.END_TAG) {
						currentTag = myXml.getName();
						if (currentTag != null)
							if (currentTag.equals("TaroCard")) {
								// add card to the map
								cards.put(card.getCardId(), card);
//								Log.d(TAG, "Card :" + card.toString());
								// card = null;
							}
						// Clear current tag
						currentTag = null;
//						Log.d(TAG, "XMLParser:getXML: End tag = " + currentTag);
					} else if (eventType == XmlPullParser.TEXT) {
						currentText = myXml.getText();
//						Log.d(TAG, "XMLParser:getXML: Text = " + currentText);

						if (currentTag != null && currentText != null) {
							if (currentTag.equals("TaroCard")) {
								card = new Card();
							} else if (currentTag.equals("Arcana")) {
								card.setArcana(currentText);
							} else if (currentTag.equals("CardGroup")) {
								card.setSuit(currentText);
							} else if (currentTag.equals("CardId")) {
								card.setCardId(Integer.parseInt(currentText) - 1);
								card.setDirectId(String.format("%02d",
										card.getCardId()));
							} else if (currentTag.equals("Card")) {
								card.setName(currentText);
							} else if (currentTag.equals("DirectReading")) {
								card.setDirectReading(currentText);
							} else if (currentTag.equals("ReverseReading")) {
								card.setReverseReading(currentText);
							} else if (currentTag.equals("Meaning")) {
								card.setMeaning(currentText);
							}
						}
					}

					eventType = myXml.next();
				}

			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return cards;

		}
	}

}
