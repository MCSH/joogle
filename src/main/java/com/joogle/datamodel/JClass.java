package com.joogle.datamodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import com.joogle.Matchable;

public class JClass implements Matchable {
	public String name;
	public String _package;
	public JAccess access;
	ArrayList<JMethod> methods = new ArrayList<>();
	ArrayList<JClass> subClasses = new ArrayList<>();

	public JClass(String _package, String name, JAccess access) {
		this._package = _package;
		this.name = name;
		this.access = access;
	}

	public void addMethod(JMethod m) {
		methods.add(m);
	}

	public String getPackage() {
		return _package;
	}

	public String toVerboseString() {
		String methods = "";
		for (JMethod m : this.methods) {
			methods += "\n* " + m;
		}
		String classes = "";
		for (JClass c : this.subClasses) {
			classes += "\n+ " + c.access + " " + c.name;
		}
		return String.format("Class %s.%s %s%s%s", _package, name, access, methods, classes);
	}

	@Override
	public String toString() {
		return String.format("%s class %s.%s", access, _package, name);
	}

	public void addClass(JClass subc) {
		subClasses.add(subc);
	}

	public ArrayList<Matchable> matches(Query inp) {
		ArrayList<Matchable> res = new ArrayList<>();
		if (inp.match(this))
			res.add(this);
		
		for(JClass sub: subClasses)
			res.addAll(sub.matches(inp));
		
		for(JMethod m: methods) {
			res.addAll(m.matches(inp));
		}
		
		// TODO run matches for all of the members
		
		return res;
	}
	
	@Override
	public String getRow() {
		return toString();
	}
	
	public String getQualifiedName() {
		return _package + "." + name;
	}
}
