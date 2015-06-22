package com.appazal.quizzle.question;

public class TextQuestion implements Question {

	private String qText;
	
	public TextQuestion(String qText) {
		this.qText = qText; 
	}
	
	public String getContent() {
		return qText;
	}
}
