package com.joogle.datamodel;

import com.github.javaparser.ast.AccessSpecifier;

public class JAccess {
	public enum a_type {
		_public, _private, _protected, _default,
	}

	public a_type access;

	public JAccess() {
	}

	public JAccess(a_type access) {
		this.access = access;
	}

	public JAccess(String inp) {
		if (inp.equals("private"))
			this.access = a_type._private;
		if (inp.equals("public"))
			this.access = a_type._public;
		if (inp.equals("protected"))
			this.access = a_type._protected;
		if (inp.equals(""))
			this.access = a_type._default;
	}

	public JAccess(AccessSpecifier as) {
		this(as.asString());
	}

	@Override
	public String toString() {

		if (access == a_type._private)
			return "private";
		if (access == a_type._public)
			return "public";
		if (access == a_type._protected)
			return "protected";
		return "";
	}
}
