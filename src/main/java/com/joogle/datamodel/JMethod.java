package com.joogle.datamodel;

import java.util.ArrayList;
import java.util.Collection;

import com.joogle.Matchable;

public class JMethod implements Matchable {

	private String name;
	private JType returnType;
	private JAccess access;
	private ArrayList<JParameter> params;
	private String jdoc;
	private JClass jclass;

	public JMethod(String name, JType jType, JAccess maccess, ArrayList<JParameter> params) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.returnType = jType;
		this.access = maccess;
		this.params = params;
	}

	public void setJavaDoc(String text) {
		this.jdoc = text;
	}

	public void setClass(JClass jc) {
		this.jclass = jc;
	}

	static public class JParameter {
		public JType jtype;
		public boolean isVararg = false;
		public int arrayDepth = 0;
		public String name;

		public JParameter(JType jtype) {
			this.jtype = jtype;
		}

		@Override
		public String toString() {
			return jtype + (isVararg ? " ..." : " ") + name + ("[]".repeat(arrayDepth));
		}
	}

	private String paramStrings() {
		String args = "";
		int len = params.size();
		for (int i = 0; i < len; i++) {
			JParameter jp = params.get(i);
			args += jp;
			if (i != len - 1)
				args += ", ";
		}
		return args;
	}

	@Override
	public String toString() {

		return String.format("method %s%s %s(%s)", access.access == JAccess.a_type._default ? "" : access + " ",
				returnType, name, paramStrings());
	}

	public ArrayList<Matchable> matches(Query inp) {
		ArrayList<Matchable> res = new ArrayList<>();
		if (inp.match(this))
			res.add(this);
		return res;
	}

	public String getPackage() {
		return jclass.getPackage();
	}

	public JType getReturnType() {
		return returnType;
	}

	@Override
	public String getRow() {
		return "method " + (access.access == JAccess.a_type._default ? "" : access + " ") + returnType + " "
				+ jclass.getQualifiedName() + "::" + name + "(" + paramStrings() + ")";
	}
}
