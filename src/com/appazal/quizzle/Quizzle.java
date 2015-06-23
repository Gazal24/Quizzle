package com.appazal.quizzle;

import java.util.DuplicateFormatFlagsException;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
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
import com.appazal.quizzle.question.Question;
import com.appazal.quizzle.question.TextQuestion;

public class Quizzle extends Activity implements Progressable<Integer>, AsyncTaskListener<Puzzle>, OptionFragmentListener {

	private static final String TAG = "Quizzle :: ";
	private Context mContext;
	private Quizzle mActivity;
	private OptionFragment[] optionFragments = new OptionFragment[Config.options_size];
	private Realm mRealm; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "in onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quizzle);
		
		mContext = getApplicationContext();
		mActivity = this;
		mRealm = Realm.getInstance(this);
		
		savePuzzle();
		Puzzle puzzle = createPuzzle();
		if(savedInstanceState == null) {
			// when activity is created for the first time.
			TextView questionView = (TextView) findViewById(R.id.question);
			questionView.setText(((TextQuestion)puzzle.getQuestion()).getText());
			questionView.setBackgroundColor(Color.parseColor(Config.QUESTION_PALETTE));
			
			setupOptionsFragment(puzzle);
		} else {
			// when activity is restored from its previous instance.
		}
		
		IntentFilter inf = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
		AirplaneModeBroadcastReceiver airplaneModeBR = new AirplaneModeBroadcastReceiver();
		mContext.registerReceiver(airplaneModeBR, inf);
	}
	
	private void setupOptionsFragment(Puzzle puzzle) {
		FragmentManager mFragmentManager = getFragmentManager();
		FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

		int id = -1;
		for(OptionFragment optionFragment : optionFragments) {
			id++;
			optionFragment = new TextOptionFragment(id, mActivity, puzzle.getOption(id));
			mFragmentTransaction.add(R.id.options_container, optionFragment, "OPTION_FRAGMENT_"+id);
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
	
	private void savePuzzle() {
		// TODO: Remove try catch from here. Handle primary key bug some other way.
		try {
//			mRealm.beginTransaction();
//			TextQuestion tQues = mRealm.createObject(TextQuestion.class);
//			tQues.setText(Config.RANDOM_QUESTION);
//			tQues.setId(2);
//			mRealm.commitTransaction();

//			TextQuestion tQues = new TextQuestion("Hello", 1); 
//			mRealm.beginTransaction();
//			mRealm.copyToRealm(tQues);
//			tQues = new TextQuestion(Config.RANDOM_QUESTION, 2);
//			myQues = mRealm.copyToRealm(tQues);
//			mRealm.commitTransaction();

		} catch (RealmException re) {
			mRealm.cancelTransaction();
			re.printStackTrace();
		}
	}
	
	private Puzzle createPuzzle() {
		Puzzle puzzle = new Puzzle();
		puzzle.setQuestion(new TextQuestion(Config.RANDOM_QUESTION));
		Option[] options = new Option[4];
		int i = 0;
		while(i<4) {
			options[i] = new TextOption(1, Config.OptionList.get(i), i == 1 ? true : false);
			i++;
		}
		puzzle.setOptions(options);
		
//		RealmQuery<TextQuestion> query = mRealm.where(TextQuestion.class);
//		query.equalTo("id", 2);
//		TextQuestion tQues = query.findFirst();
//		Log.i(TAG, "Returning Stored Content " + tQues.getText());
		return puzzle;
	}
	
	// It is called from layout 
	public void onFetchPuzzleButtonClick(View v){
		Log.i(TAG, "I'm going to fetch content");
		new DownloadPuzzleTask(mActivity, mActivity).execute(5);
		
		sendBroadcast(new Intent("what-nonsense").putExtra("quota", 50));
	}
	
   private Puzzle readPuzzleDb(int id){
       RealmQuery<TextQuestion> qQuery = mRealm.where(TextQuestion.class);
       qQuery.equalTo("id", id);
	   TextQuestion ques = qQuery.findFirst();
	   
	   RealmQuery<TextOption> oQuery = mRealm.where(TextOption.class);
	   oQuery.equalTo("quesId", id);
	                
	   RealmResults<TextOption> optionList = oQuery.findAll();
	   
	   Puzzle puzzle = new Puzzle();
	   Option[] options = new Option[optionList.size()];
	   puzzle.setQuestion(ques);
	   puzzle.setOptions(optionList.toArray(options));
	   Log.i(TAG, "Returning Stored Content " + ques.getText());
	   return puzzle;
    }
 

	@Override
	public void updateProgressMeter(Integer progress) {
		Log.i(TAG, "Will update progress 1");
		TextView mProgressView = (TextView) findViewById(R.id.progressMeter);
		mProgressView.setText(progress.toString());
	}

	@Override
	public void onPostExecute(Puzzle[] result) {
		mRealm.beginTransaction();
//		mRealm.copyToRealm(new TextQuestion("hello", 2));
		for(Puzzle p : result) {
			try {
				mRealm.copyToRealm((TextQuestion)(p.getQuestion()));
				mRealm.copyToRealm((TextOption)p.getOptions()[0]);
			} catch (RealmException re) {
				re.printStackTrace();
			}
		}
		mRealm.commitTransaction();
		Log.i(TAG, ((TextQuestion)(result[2].getQuestion())).getText());
		Puzzle puzzle = readPuzzleDb(1);
		Log.i(TAG, "Option 1 : " + ((TextOption)puzzle.getOptions()[0]).getText());
	}

	@Override
	public void onClickCallback(Option o) {
		Log.i(TAG, ((TextOption)o).getIsCorrect() + "");
	}
}
