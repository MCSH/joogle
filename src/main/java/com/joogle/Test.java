package com.joogle;

import java.util.ArrayList;

import com.joogle.datamodel.DataBase;
import com.joogle.datamodel.JClass;
import com.joogle.gui.Joogle;
import com.joogle.query.QueryHandler;

public class Test {
	/**
	 * Something
	 * 
	 * @param as
	 */
	public static void main(String[] args) {

		ArrayList<String> directories = new ArrayList<>();

		directories.add("/home/sajjad/src/manitoba/5.classes/2020_02/ddse/project/Joogle/src/main/java");
//		directories.add("/home/sajjad/src/manitoba/5.classes/2020_02/ddse/java_source/src");
		
		
		System.out.println("Starting");
		for (String d : directories) {
			System.out.println("Processing directory: " + d);
			Explorer e = new Explorer(d);
			e.extract(); // No need to capture the output, everything is stored in the DataBase
		}
		
//		DataBase.getInstance().test();

		new QueryHandler("class me");
		new QueryHandler("class *");
		System.out.println("Done");

//		Joogle.run();
	}

	class TestSubClass {

	}

}

class Test2 {
	int intMethod(int b[][]) {
		return 0;
	}

	void voidMethod(int... a) {

	}
}