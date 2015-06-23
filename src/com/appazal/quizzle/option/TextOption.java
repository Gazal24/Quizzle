package com.appazal.quizzle.option;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class TextOption extends RealmObject implements Option {

	@PrimaryKey
	private String text;
	private boolean isCorrect;
	
	public TextOption() {}
	
	public TextOption (String option_text, boolean isCorrect) {
		this.text = option_text;
		this.isCorrect = isCorrect;
	}
	
	/*
	 * Constructor to initialize Option only with text and assumes it to be incorrect.
	 */
	public TextOption (String option_text) {
		this.text= option_text;
		this.isCorrect = false;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public void setIsCorrect(boolean isCorrect){
		this.isCorrect = isCorrect;
	}
	
	public boolean getIsCorrect() {
		return isCorrect;
	}
	
}