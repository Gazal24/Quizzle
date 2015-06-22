package com.appazal.quizzle.question;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ImageQuestion implements Question {
	
	@SuppressWarnings("deprecation")
	private Drawable imgQues = new BitmapDrawable("@drawable/QuestionNumber1.png");
	
	public ImageQuestion(String imgPath){
		imgQues = new BitmapDrawable(imgPath);
	}
	
	public ImageQuestion(Drawable drw) {
		imgQues = drw;
	}
	
	public Drawable getContent() {
		return imgQues;
	}
}
