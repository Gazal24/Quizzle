package com.appazal.quizzle;

import com.appazal.quizzle.option.Option;
import com.appazal.quizzle.question.Question;

public class Puzzle {

	private Question question;
	private Option[] options = new Option[Config.options_size];
	
	public Puzzle() {}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public Question getQuestion() {
		return this.question;
	}
	
	public void setOptions(Option[] options) {
		this.options = options;
	}
	
	public Option[] getOptions() {
		return this.options;
	}
	
	public Option getOption(int index) {
		return this.options[index];
	}
}
