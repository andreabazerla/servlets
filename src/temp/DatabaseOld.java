package temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.andreabazerla.database.file.AbstractDatabase;
import com.andreabazerla.person.Person;

public class DatabaseOld extends AbstractDatabase<Person> {

	private List<Person> personList = new ArrayList<Person>();
	private String filePath;
	
	public DatabaseOld(String filePath) throws IOException {
		super(filePath);
	}
	
	@Override
	protected void readFile(String filePath) throws IOException {
		
		FileReader fileReader = new FileReader(filePath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String row;
		while ((row = bufferedReader.readLine()) != null) {
			String[] field = row.split(",");
			Person person = new Person(field[0], field[1], field[2]);
			personList.add(person);
		}
	}
	
	@Override
	public void updateDatabase() throws IOException {
		
		PrintWriter cleaner = new PrintWriter(filePath);
		cleaner.print("");
		cleaner.close();
		
		PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));

		for (Person person : personList) {
			String row = person.getCf() + "," + person.getName() + "," + person.getSurname();
		    printWriter.println(row);
		}
		
		printWriter.close();
	}
	
	@Override
	public Person getUser(String cf) throws IOException {
		for (Person person : personList) {
			if (person.getCf().equals(cf)) {
				return person;
			}
		}
		return null;
	}
	
	@Override
	public void setUser(String cf, String name, String surname) throws IOException {		
		Person person = new Person(cf.trim(), name.trim(), surname.trim());
	    personList.add(person);
	}
	
	@Override
	public void deleteUser(String cf) throws IOException {
		Person person = this.getUser(cf);
		personList.remove(person);
	}

	@Override
	public ArrayList<Person> getAll() throws IOException {		
		return (ArrayList<Person>) personList;
	}
	
}
