package com.orka.taropro;

import java.util.ArrayList;
import java.util.List;
import com.orka.taropro.domain.Card;
import com.orka.taropro.domain.CardType;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

public class DeckBrowseActivity extends ExpandableListActivity {

	private static final String TAG = DeckBrowseActivity.class.getSimpleName();

	ExpandableListAdapter adapter;

	TaroApplication taro;
	List<Group> groups = new ArrayList<Group>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);

		taro = (TaroApplication) getApplication();

		String[] cardGroups = getResources().getStringArray(R.array.cardGroups);

		for (int i = 0; i < cardGroups.length; i++) {
			Group group = new Group();
			group.setGroup(cardGroups[i]);
			group.setChildCards(taro.cardHelper.cardDeck.getCardsArray(CardType.UNKNOWN.getByName(cardGroups[i])));
			
			groups.add(group);
		}

		adapter = new DeckAdapter(this, groups);
		setListAdapter(adapter);

		View currentView = this.getExpandableListView();
		currentView.setBackgroundResource(R.drawable.background11);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	private class Group {
		private String group;
		private List<String> child = null;
		private List<Card> childCards = null;

		public boolean hasCards() {
			return child == null && childCards != null;
		}

		public String getGroup() {
			return group;
		}

		public void setGroup(String group) {
			this.group = group;
		}

		public List<String> getChild() {
			return child;
		}

		public void setChild(List<String> child) {
			this.child = child;
		}

		public List<Card> getChildCards() {
			return childCards;
		}

		public void setChildCards(List<Card> childCards) {
			this.childCards = childCards;
		}

		public String getChildString(int position) {
			if (child == null) {
				return childCards.get(position).getName();
			} else {
				if (childCards == null) {
					return child.get(position);
				}
			}

			return "";
		}

		public int getChildCount() {
			if (child == null) {
				return childCards.size();
			} else {
				if (childCards == null) {
					return child.size();
				}
			}

			return 0;
		}

	}

	private class DeckAdapter extends BaseExpandableListAdapter {

		private Context context;
		List<Group> groups;

		public DeckAdapter(Context context, List<Group> groups) {
			this.context = context;
			this.groups = groups;
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {

			return groups.get(groupPosition).getChildString(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {

			return childPosition;
		}

		public TextView getGenericView() {
			// Layout parameters for the ExpandableListView
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, 64);

			TextView textView = new TextView(context);

			textView.setTextAppearance(context,
					android.R.style.TextAppearance_Large);
			textView.setTextColor(Color.parseColor("#FFFF77"));

			textView.setLayoutParams(lp);
			// Center the text vertically
			textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			// Set the text starting position
			textView.setPadding(72, 0, 0, 0);
			return textView;
		}

		
		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
				TextView textView = getGenericView();
				textView.setText(getChild(groupPosition, childPosition)
						.toString());
				return textView;

		}

		@Override
		public int getChildrenCount(int groupPosition) {

			return groups.get(groupPosition).getChildCount();
		}

		@Override
		public Object getGroup(int groupPosition) {

			return groups.get(groupPosition).getGroup();
		}

		@Override
		public int getGroupCount() {

			return groups.size();
		}

		@Override
		public long getGroupId(int groupPosition) {

			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			TextView textView = getGenericView();
			textView.setText(getGroup(groupPosition).toString());
			return textView;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {

			return true;
		}

		@Override
		public boolean hasStableIds() {

			return true;
		}

	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {

		Toast.makeText(taro,
				groups.get(groupPosition).getChildString(childPosition),
				Toast.LENGTH_LONG).show();

		if (groups.get(groupPosition).hasCards()) {
			taro.setCurrentCard(groups.get(groupPosition).getChildCards()
					.get(childPosition));
			startActivity(new Intent(DeckBrowseActivity.this,
					CardActivity.class));
		}

		return true;
	}

}
