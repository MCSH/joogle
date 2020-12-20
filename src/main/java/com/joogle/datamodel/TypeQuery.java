package com.joogle.datamodel;

public class TypeQuery {
	private int arrayDepth = 0; // TODO not handled yet
	private String name;

	public int getArrayDepth() {
		return arrayDepth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean match(JType returnType) {
		if (!returnType.name.equals(name))
			return false;
		return true;
	}
}
