package com.joogle.datamodel;

public class JType {

	String name;
	// TODO handle arrays and stuff

	public JType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
