package com.appazal.quizzle;

public interface AsyncTaskListener<Result> {
	void onPostExecute(Result[] result);
}
