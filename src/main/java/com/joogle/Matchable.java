package com.joogle;

import java.util.ArrayList;

import com.joogle.datamodel.Query;

public interface Matchable {
	public ArrayList<Matchable> matches(Query inp);

	public String getRow();
}
