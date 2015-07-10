package com.appazal.quizzle.model.question;


public class TextQuestion extends Question {

	private String text;
	
	public TextQuestion() {}
	
	public TextQuestion(String text) {
		this.text = text; 
	}
	
	public TextQuestion(int id, String text) {
		super(id);
		this.text = text; 
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}
