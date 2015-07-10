package com.appazal.quizzle.model.option;


public class TextOption extends Option {

	private String text;
	
	public TextOption() {}
	
	// Constructor to initialize Option only with text and assumes it to be incorrect.
	public TextOption (int questionId, String option_text) {
		this(questionId, option_text, false);
	}

	public TextOption (int questionId, String option_text, boolean isCorrect) {
		super(questionId, isCorrect);
		this.text = option_text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}