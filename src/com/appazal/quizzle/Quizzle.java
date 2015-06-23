package com.appazal.quizzle;

import io.realm.Realm;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.appazal.quizzle.option.Option;
import com.appazal.quizzle.option.OptionFragment;
import com.appazal.quizzle.option.OptionFragmentListener;
import com.appazal.quizzle.option.TextOption;
import com.appazal.quizzle.option.TextOptionFragment;
import com.appazal.quizzle.question.TextQuestion;

public class Quizzle extends Activity implements Progressable<Integer>, AsyncTaskListener<Puzzle>, OptionFragmentListener {

	private static final String TAG = "Quizzle :: ";
	private Context mContext;
	private Quizzle mActivity;
//	private OptionFragment[] optionFragments = new OptionFragment[Config.options_size];
	private Realm mRealm;
	private int mCurrentPuzzleId = 0; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "in onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quizzle);
		
		mContext = getApplicationContext();
		mActivity = this;
		mRealm = Realm.getInstance(this);
		
		Puzzle puzzle = MockImplementations.createPuzzle();
		if(savedInstanceState == null) {
			// when activity is created for the first time.
			displayPuzzle(puzzle);
		} else {
			// when activity is restored from its previous instance.
		}
		
		IntentFilter inf = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
		AirplaneModeBroadcastReceiver airplaneModeBR = new AirplaneModeBroadcastReceiver();
		mContext.registerReceiver(airplaneModeBR, inf);
	}
	
	private void displayPuzzle(Puzzle puzzle) {
		TextView questionView = (TextView) findViewById(R.id.question);
		questionView.setText(((TextQuestion)puzzle.getQuestion()).getText());
		
		setupOptionsFragment(puzzle);
	}

	private void setupOptionsFragment(Puzzle puzzle) {
		FragmentManager mFragmentManager = getFragmentManager();
		FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

		int index = 0;
		OptionFragment optionFragment;
		Log.i(TAG, "Options count : " + puzzle.getOptions().length+"");
		for(Option option : puzzle.getOptions()) {
			optionFragment = new TextOptionFragment(index, mActivity, option);
			Fragment fExist;
			if((fExist = mFragmentManager.findFragmentByTag("OPTION_FRAGMENT_"+index)) != null)
				mFragmentTransaction.remove(fExist);
			mFragmentTransaction.add(R.id.options_container, optionFragment, "OPTION_FRAGMENT_"+index);
			index++;
		}
		mFragmentTransaction.commit();
	}

	protected void onStart() {
		Log.i(TAG, "in onStart");
		super.onStart();
	}
	
	protected void onResume(){
		Log.i(TAG, "in onResume");
		super.onResume();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
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
	
	// It is called from layout 
	public void onFetchPuzzleButtonClick(View v) {
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
		Puzzle.savePuzzlesDb(mRealm, result);
	}

	@Override
	public void onClickCallback(Option o) {
		Log.i(TAG, ((TextOption)o).getIsCorrect() + "");
		if(((TextOption)o).getIsCorrect())
			displayPuzzle(Puzzle.readPuzzleDb(mRealm, getNextPuzzleId()));
		else {
			Toast t = Toast.makeText(mContext, "Try Again", Toast.LENGTH_SHORT);
			t.show();
		}
	}

	private int getNextPuzzleId(){
		return ++mCurrentPuzzleId  % 5;
	}
}
