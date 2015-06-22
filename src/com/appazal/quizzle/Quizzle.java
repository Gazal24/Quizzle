package com.appazal.quizzle;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.appazal.quizzle.option.Option;
import com.appazal.quizzle.option.OptionFragment;
import com.appazal.quizzle.option.OptionFragmentListener;
import com.appazal.quizzle.option.TextOption;
import com.appazal.quizzle.option.TextOptionFragment;
import com.appazal.quizzle.question.TextQuestion;

public class Quizzle extends Activity implements Progressable<Integer>, AsyncTaskListener<Puzzle>, OptionFragmentListener{

	private static final String TAG = "Quizzle :: ";
	private Context mContext;
	private Quizzle mActivity;
	private OptionFragment[] optionFragments = new OptionFragment[Config.options_size];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "in onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quizzle);
		
		mContext = getApplicationContext();
		mActivity = this;

		if(savedInstanceState == null) {
			// when activity is created for the first time.
			TextView questionView = (TextView) findViewById(R.id.question);
			questionView.setText(Config.RANDOM_QUESTION);
			questionView.setBackgroundColor(Color.parseColor(Config.QUESTION_PALETTE));
			
			setupOptionsFragment();
		} else {
			// when activity is restored from its previous instance.
		}
		
		IntentFilter inf = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
		AirplaneModeBroadcastReceiver airplaneModeBR = new AirplaneModeBroadcastReceiver();
		mContext.registerReceiver(airplaneModeBR, inf);
	}
	
	private void setupOptionsFragment(){
		FragmentManager mFragmentManager = getFragmentManager();
		FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

		int id = -1;
		for(OptionFragment optionFragment : optionFragments) {
			id++;
			optionFragment = new TextOptionFragment(id, mActivity, new TextOption(Config.OptionList.get(id), id == 1 ? true : false));
			mFragmentTransaction.add(R.id.options_container, optionFragment, "OPTION_FRAGMENT_"+id);
		}

		mFragmentTransaction.commit();
	}
		
	protected void onStart(){
		Log.i(TAG, "in onStart");
		super.onStart();
	}
	
	protected void onResume(){
		Log.i(TAG, "in onResume");
		super.onResume();
		
		Intent i = new Intent();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState){
		Log.i(TAG, "in OnSaveInstanceState");
		super.onSaveInstanceState(outState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.quizzle, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
//	@SuppressWarnings("unused") // It is called from layout 
	public void onFetchPuzzleButtonClick(View v){
		Log.i(TAG, "I'm going to fetch content");
		new DownloadPuzzleTask(mActivity, mActivity).execute(5);
		
		sendBroadcast(new Intent("what-nonsense").putExtra("quota", 50));
	}

	@Override
	public void updateProgressMeter(Integer progress) {
		Log.i(TAG, "Will update progress 1");
		TextView mProgressView = (TextView) findViewById(R.id.progressMeter);
		mProgressView.setText(progress.toString());
	}

	@Override
	public void onPostExecute(Puzzle[] result) {
		Log.i(TAG, ((TextQuestion)(result[2].getQuestion())).getContent());
	}

	@Override
	public void onClickCallback(Option o) {
		Log.i(TAG, o.isCorrect() + "");
	}
}
