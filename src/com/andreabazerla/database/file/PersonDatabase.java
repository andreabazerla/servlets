package com.andreabazerla.database.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.andreabazerla.person.Person;

public class PersonDatabase extends AbstractDatabase<Person> {

	public PersonDatabase(String filePath) throws IOException {
		super(filePath);
	}

	@Override
	public Person create(String row) {
		return Person.create(row);
	}

	@Override
	public Person load(final String cf) throws IOException {
		ComparatorDatabase<Person> c = new ComparatorDatabase<Person>() {

			@Override
			public boolean equals(Person x) {
				if (x == null)
					return false;
				return x.getCf().equals(cf);
			}
		};

		return internalLoad(c);
	}

	@Override
	public void save(Person t) {
		addElement(t);
	}
	
	@Override
	public void delete(String cf) throws IOException {
		Person person = load(cf);
		removeElement(person);
	}

	@Override
	public ArrayList<Person> getAll(String pattern) {
		
		String patternPurified = pattern.trim();
		
		if (pattern.trim() == null || (pattern.trim()).isEmpty())
			return null;

		List<Person> resultList = new ArrayList<Person>();

		for (Person person : getAll("")) {
			if (person.getCf().toLowerCase().contains(patternPurified)
					|| person.getName().toLowerCase().contains(patternPurified)
					|| person.getSurname().toLowerCase().contains(patternPurified)) {
				resultList.add(person);
			}
		}

		return (ArrayList<Person>) resultList;
	}
}
