package com.orka.taropro;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class SpreadDescriptionDialog {
	
	public static final int DIALOG_ONE_CARD = 1;
	public static final int DIALOG_HORSESHOE = 2;
	public static final int DIALOG_CELTIC_CROSS = 3;
	public static final int DIALOG_THREE_CARS = 4;
	public static final int DIALOG_MIRROR = 5;
		
	private Context context;
		
	public SpreadDescriptionDialog(Context context) {
		this.context = context;
	}
	
	protected Builder createDialogBuilder(final int id) {
		String meaning = "";
		String description = "";

		switch (id) {
		case DIALOG_ONE_CARD:
			meaning = context.getResources().getString(R.string.dialog_title_one_card);
			description = context.getResources().getString(
					R.string.dialog_description_one_card);
			break;
		case DIALOG_HORSESHOE:
			meaning = context.getResources().getString(R.string.dialog_title_horseshoe);
			description = context.getResources().getString(
					R.string.dialog_description_horseshoe);
			break;
		case DIALOG_CELTIC_CROSS:
			meaning = context.getResources().getString(
					R.string.dialog_title_celtic_cross);
			description = context.getResources().getString(
					R.string.dialog_description_celtic_cross);
			break;
		case DIALOG_THREE_CARS:
			meaning = context.getResources().getString(
					R.string.dialog_title_threecard);
			description = context.getResources().getString(
					R.string.dialog_description_threecard);
			break;
		case DIALOG_MIRROR:
			meaning = context.getResources().getString(
					R.string.dialog_title_mirror);
			description = context.getResources().getString(
					R.string.dialog_description_mirror);
			break;

		}

		return new AlertDialog.Builder(context)
				.setIcon(R.drawable.taro)
				.setTitle(meaning)
				.setMessage(description)
				.setPositiveButton(R.string.dialog_button_go,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								switch (id) {
								case DIALOG_ONE_CARD:
									context.startActivity(new Intent(context,
											OneCardActivity.class));
									break;
								case DIALOG_HORSESHOE:
									context.startActivity(new Intent(context,
											HorseshoeActivity.class));
									break;
								case DIALOG_CELTIC_CROSS:
									context.startActivity(new Intent(context,
											CelticCrossActivity.class));
									break;
								case DIALOG_THREE_CARS:
									context.startActivity(new Intent(context,
											ThreeCardActivity.class));
									break;
								case DIALOG_MIRROR:
									context.startActivity(new Intent(context,
											MirrorActivity.class));
									break;

								}
							}
						})
				.setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								/* User clicked Cancel so do some stuff */
							}
						});
	}
	
	public void showDialog(int dialogId){
		AlertDialog.Builder builder = createDialogBuilder(dialogId);
		builder.show();
	}


}
