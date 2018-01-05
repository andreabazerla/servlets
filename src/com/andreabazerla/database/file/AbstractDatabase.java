package com.andreabazerla.database.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.andreabazerla.CSV;
import com.andreabazerla.database.IGenericDao;

public abstract class AbstractDatabase<T extends CSV> implements IGenericDao<T>  {

	protected interface ComparatorDatabase<X> {
		public boolean equals(X x);
	}

	private List<T> list = new ArrayList<T>();
	private String filePath;

	protected AbstractDatabase(String filePath) throws IOException {
		this.filePath = filePath;
		readFile(filePath);
	}

	protected String getFilepath() {
		return this.filePath;
	}

	protected void addElement(T e) {
		list.add(e);
	}

	protected void removeElement(T e) {
		list.remove(e);
	}

	protected void readFile(String filePath) throws IOException {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		try {
			fileReader = new FileReader(filePath);
			try {
				bufferedReader = new BufferedReader(fileReader);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

		String row;
		while ((row = bufferedReader.readLine()) != null) {
			T t = null;
			try {
				t = create(row);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			addElement(t);
		}
		
		bufferedReader.close();
	}

	public final void updateDatabase() throws IOException {
		PrintWriter cleaner = new PrintWriter(getFilepath());
		cleaner.print("");
		cleaner.close();

		PrintWriter printWriter = new PrintWriter(new BufferedWriter(
				new FileWriter(getFilepath(), true)));

		for (T t : getAll("")) {
			String row = t.getRow();
			printWriter.println(row);
		}

		printWriter.close();
	}

	protected T internalLoad(ComparatorDatabase<T> c) throws IOException {
		for (T t : getAll("")) {
			if (c.equals(t)) {
				return t;
			}
		}
		return null;
	}

	public abstract T load(String key) throws IOException;

	public abstract void save(T t);

	public abstract void delete(String cf) throws IOException;

	@Override
	public ArrayList<T> getAll(String search) {
		return (ArrayList<T>) list;
	}
	
	public abstract T create(String row) throws ParseException;

}
