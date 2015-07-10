package com.appazal.quizzle.model;

public class Topic {

	private int id;
	private String name;

	public Topic(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setId(int id) { this.id = id; }

	public int getId() { return this.id; }

	public void setName(String name) { this.name = name; }

	public String getName() { return this.name; }
}
