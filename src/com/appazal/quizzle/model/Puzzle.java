package com.appazal.quizzle.model;

import com.appazal.quizzle.model.option.Option;
import com.appazal.quizzle.model.question.Question;

public class Puzzle {

	private Topic[] topics;
	private Question question;
	private Option[] options;

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

	public void setTopics(Topic[] topics) {
		this.topics = topics;
	}

	public Topic[] getTopics() {
		return this.topics;
	}
}

