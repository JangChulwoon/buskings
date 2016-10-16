package com.busking.bean;

public class User {
	private String id;
	private String name;
	private String pass;
	private String location;
	public User(String id, String name, String pass,String location) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		return pass;
	}

}
