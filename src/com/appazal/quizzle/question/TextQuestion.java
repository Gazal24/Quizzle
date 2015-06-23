package com.appazal.quizzle.question;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class TextQuestion extends RealmObject implements Question{

	@PrimaryKey
	private int id;
	
	private String text;
	
	public TextQuestion(){
		
	}
	
	public TextQuestion(String text) {
		this.text = text; 
	}
	
	public TextQuestion(String text, int id) {
		this.text = text; 
		this.id = id;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
