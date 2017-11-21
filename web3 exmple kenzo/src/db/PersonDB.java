package db;

import java.util.List;

import domain.Person;

public interface PersonDB {
	Person get(String personId);
	List<Person> getAll();
	void add(Person p);
	void update(Person p);
	void delete(String personId);
}
