package com.appazal.quizzle.model.question;

import com.appazal.quizzle.model.iGsonTypeSetter;

public abstract class Question implements iGsonTypeSetter {

	protected int id;
	protected String type; 	/* Set 'type' for Gson Deserialization */
	
	public Question() {
		setType();
	}
	
	public Question(int id) {
		this();
		this.id = id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public final void setType() {
		this.type = getClass().getSimpleName();
	}
	
	public final String getType() { return this.type; }
}
