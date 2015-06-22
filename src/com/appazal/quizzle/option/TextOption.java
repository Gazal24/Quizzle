package com.appazal.quizzle.option;

public class TextOption implements Option {

	private String option_text;
	private boolean isCorrect;
	
	public TextOption (String option_text, boolean isCorrect) {
		this.option_text = option_text;
		this.isCorrect = isCorrect;
	}
	
	/*
	 * Constructor to initialize Option only with text and assumes it to be incorrect.
	 */
	public TextOption (String option_text) {
		this.option_text= option_text;
		this.isCorrect = false;
	}
	
	public String optionText(){
		return option_text;
	}
	
	@Override
	public boolean isCorrect(){
		return isCorrect;
	}
}
