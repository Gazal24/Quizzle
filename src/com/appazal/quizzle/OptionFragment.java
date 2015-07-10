package com.appazal.quizzle;

import com.appazal.quizzle.model.option.Option;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public abstract class OptionFragment extends Fragment{
	
	protected static final String TAG = "Quizzle OptionFragment :: ";
	protected int id;
	protected Option option;
	protected OptionFragmentListener optionFragmentListener;
	
	protected OptionFragment(int id, OptionFragmentListener o) {
		Log.i(TAG, "in param constructor");
		this.id = id;
		this.optionFragmentListener = o;
	}
	
	public OptionFragment(){
		Log.i(TAG, "in empty Constructor");
	}
	
	public Option getOption() {
		return option;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		if(savedInstanceState != null) {
			if(savedInstanceState.getInt("state_changed") == 1) {
//				this.config = new Config();
			}
			this.id = savedInstanceState.getInt("state_changed");
		}
		setRetainInstance(true);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		Log.i(TAG, "in onSaveInstanceState");
		outState.putInt("state_changed", 1);
		outState.putInt("id", id);
	}
	
	@Override
	public abstract View onCreateView(LayoutInflater layoutInflater,
			ViewGroup container, Bundle savedInstaceState);
	
	protected String constructViewId(int id) {
		return "option_"+id;
	}

	public void setListener(View optionView) {
		optionView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				optionFragmentListener.onClickCallback(option);
			}
		});
	}
}
