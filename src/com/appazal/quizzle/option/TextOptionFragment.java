package com.appazal.quizzle.option;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appazal.quizzle.Config;
import com.appazal.quizzle.R;

public class TextOptionFragment extends OptionFragment{

	public TextOptionFragment (int id, OptionFragmentListener o, Option option) {
		super(id, o);
		this.option = option;
	}
	
	@Override
	public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstaceState) {
		Log.i(TAG, "in onCreateView");
		TextView optionTextView = (TextView) layoutInflater.inflate(R.layout.option_fragment, container, false);
		optionTextView.setTag(constructViewId(id));
		optionTextView.setText(((TextOption) option).optionText());
		optionTextView.setBackgroundColor(Color.parseColor(Config.OPTIONS_PALETTE.get(id)));
		super.setListener(optionTextView);
		return optionTextView;
	}
}
