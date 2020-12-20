package com.joogle.datamodel;

import java.util.ArrayList;

import com.joogle.Matchable;

public class DataBase {
	private static DataBase INSTANCE = new DataBase();

	private ArrayList<JClass> classes = new ArrayList<>();

	private DataBase() {
	}

	public static DataBase getInstance() {
		return INSTANCE;
	}

	public static void addClass(JClass jc) {
		INSTANCE.classes.add(jc);
	}

	public ArrayList<Matchable> query(Query inp) {
		ArrayList<Matchable> ans = new ArrayList<>();

		for (JClass c : classes) {
			ans.addAll(c.matches(inp));
		}

		return ans;
	}

	public void test() {

		Query sQuery = Query.TestQuery();

		ArrayList<Matchable> res = query(sQuery);

		for (Matchable o : res) {
			System.out.println("* " + o.getRow());
		}
	}
}
