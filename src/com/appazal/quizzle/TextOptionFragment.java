package com.appazal.quizzle;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appazal.quizzle.R;
import com.appazal.quizzle.model.option.Option;
import com.appazal.quizzle.model.option.TextOption;

public class TextOptionFragment extends OptionFragment{

	public TextOptionFragment (int id, OptionFragmentListener o, Option option) {
		super(id, o);
		this.option = option;
	}

	@Override
	public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstaceState) {
		TextView optionTextView = (TextView) layoutInflater.inflate(R.layout.option_fragment, container, false);
		optionTextView.setTag(constructViewId(id));
		optionTextView.setText(((TextOption) option).getText());
		optionTextView.setBackgroundColor(Color.parseColor(Config.OPTIONS_PALETTE.get(id)));
		super.setListener(optionTextView);
		return optionTextView;
	}
}
