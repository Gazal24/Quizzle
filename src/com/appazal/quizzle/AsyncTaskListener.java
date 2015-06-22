package com.appazal.quizzle;

public interface AsyncTaskListener<Result> {
	public void onPostExecute(Result[] result);
}
