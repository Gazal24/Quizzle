package com.appazal.quizzle.option;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class TextOption extends RealmObject implements Option {

	@PrimaryKey
	private int quesId;
	private String text;
	private boolean isCorrect;
	
	public TextOption() {}
	
	/*
	 * Constructor to initialize Option only with text and assumes it to be incorrect.
	 */
	public TextOption (int ques_id, String option_text) {
		this(ques_id, option_text, false);
	}

	public TextOption (int ques_id, String option_text, boolean isCorrect) {
		this.quesId = ques_id;
		this.text = option_text;
		this.isCorrect = isCorrect;
	}
	
	public void setQuesId(int id){
		this.quesId = id;
	}

	public int getQuesId() {
		return this.quesId;
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