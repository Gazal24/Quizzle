package com.appazal.quizzle.model.option;

import com.appazal.quizzle.model.iGsonTypeSetter;

public abstract class Option implements iGsonTypeSetter {
	
	private int questionId;
	private boolean isCorrect;
	private String type; /* Set 'type' for Gson deserialization */
	
	public Option() {
		setType();
	}
	
	public Option(int questionId, boolean isCorrect) {
		this();
		this.questionId = questionId;
		this.isCorrect = isCorrect;
	}

	public void setQuestionId(int id){
		this.questionId = id;
	}

	public int getQuestionId() {
		return this.questionId;
	}

	public void setIsCorrect(boolean isCorrect){
		this.isCorrect = isCorrect;
	}
	
	public boolean getIsCorrect() {
		return isCorrect;
	}
	
	@Override
	public void setType() {
		this.type = getClass().getSimpleName();
	}
}
