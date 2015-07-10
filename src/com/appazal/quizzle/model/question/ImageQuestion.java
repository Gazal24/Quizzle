package com.appazal.quizzle.model.question;


public class ImageQuestion extends Question {

	private String imagePath;
	
	public ImageQuestion() {
		setType();
	}

	public ImageQuestion(int id, String imgPath) {
		super(id);
		this.imagePath = imgPath;
	}

	public ImageQuestion(String imgPath) {
		this.imagePath = imgPath;
	}

	public String getContent() {
		return this.imagePath;
	}
}
