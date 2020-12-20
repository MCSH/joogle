package com.joogle.datamodel;

import java.util.regex.Pattern;

public class QueryBak {

	private Pattern pkg;
	private TypeQuery returnType;


	public void setPackage(String pkg) {
		pkg = pkg.replace("*", ".*");
		this.pkg = Pattern.compile("^" + pkg + "$", Pattern.CASE_INSENSITIVE);
	}

	public static QueryBak TestQuery() {
		QueryBak q = new QueryBak();
		q.setPackage("com.joogle");
		q.returnType = new TypeQuery();
		q.returnType.setName("void");
		return q;
	}

	private boolean matchPkg(String pkg) {
		if (this.pkg == null)
			return true;
		return this.pkg.matcher(pkg).find();
	}

	public boolean match(JClass jc) {
		if (this.returnType != null)
			return false;
		if (!matchPkg(jc.getPackage()))
			return false;
		return true;
	}

	public boolean match(JMethod jm) {
		if (!matchPkg(jm.getPackage()))
			return false;
		if (returnType != null && !returnType.match(jm.getReturnType()))
			return false;
		return true;
	}

}
