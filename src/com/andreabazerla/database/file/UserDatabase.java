package com.andreabazerla.database.file;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import com.andreabazerla.user.User;

public class UserDatabase extends AbstractDatabase<User> {
	
	public UserDatabase(String filePath) throws IOException {
		super(filePath);
	}
	
	@Override
	public User create(String row) throws ParseException {
		return User.create(row);
	}

	@Override
	public User load(final int id) throws IOException {
		ComparatorDatabase<User> c = new ComparatorDatabase<User>() {
			
			@Override
			public boolean equals(User x) {
				if (x == null)
					return false;
				return x.getUsername().equals(id);
			}
		};
		
		return internalLoad(c);
	}

	@Override
	public void save(User t) {
	    addElement(t);
	}

	@Override
	public void delete(int id) throws IOException {
		User user = load(id);
		removeElement(user);
	}

	@Override
	public ArrayList<User> getAll(String pattern) {
		return null;
	}
	
}
