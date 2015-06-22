package com.appazal.quizzle;

import android.os.AsyncTask;
import android.util.Log;

public class DownloadPuzzleTask extends AsyncTask<Integer, Integer, Puzzle[]> {

	private static final String TAG = "Download Puzzle";
	private final Progressable<Integer> mProgressable;
	private final AsyncTaskListener<Puzzle> mAsyncListener;
	
	public DownloadPuzzleTask(Progressable<Integer> p, AsyncTaskListener<Puzzle> a){
		mProgressable = p;
		mAsyncListener = a;
	}
	
	@Override 
	protected void onPreExecute(){
		Log.i(TAG, "in onPreExecute");
	}
	
	@Override
	protected Puzzle[] doInBackground(Integer... params) {
		Log.i(TAG, "in doIn backgoroudn");
		// Able to make HTTPS request.
		// 
		// Make a http get api call with params to puzzles server and gets a JSON of puzzles.
		// Parses the JSON and create Puzzle Object Array.
		Puzzle[] puzzles = new Puzzle[params[0]];
		
		// Faking Download
		int count = 0;
		while(count < puzzles.length) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			publishProgress(count++ * 100 /puzzles.length);
		}
		puzzles = MockImplementations.getPuzzles(params[0]);
		return puzzles;
	}
	
	@Override
	protected void onProgressUpdate(Integer... progress){
		Log.i(TAG, "in on progress update");
		mProgressable.updateProgressMeter(progress[0]);
	}
	
	protected void onPostExecute(Puzzle[] result) {
		mAsyncListener.onPostExecute(result);
	}
}
