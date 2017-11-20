package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.Person;

public class PersonDbSQL implements PersonDb{
	Properties properties;
	String url;
	
	public PersonDbSQL(Properties properties){
		this.properties = properties;
		this.url = properties.getProperty("url");
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	@Override
	public Person get(String personId) {
		if(personId == null || personId.trim().isEmpty()){ throw new DbException("Give a valid personID"); }	
		Person person = null;
		
		String sql = "SELECT * FROM r0668325.person WHERE userid = + ? ";
		
		try ( Connection connection = DriverManager.getConnection(url, properties); PreparedStatement statement = connection.prepareStatement(sql); ){
			statement.setString(1, personId);
			ResultSet result = statement.executeQuery();
		
		while(result.next()){
			String userid = result.getString("userid");
			String email = result.getString("email");
			String password = result.getString("password");
			String firstname = result.getString("firstname");
			String lastname = result.getString("lastname");
			
			person = new Person(userid, email, password, firstname, lastname);
			}
			if(person == null){ throw new DbException("Geef een geldig personID."); } else { return person; } }catch (SQLException e) { throw new DbException(e.getMessage(), e); }			
	}
	
	@Override
	public List<Person> getAll() {
		List<Person> persons = new ArrayList<>();
		Person person = null;
		
		try ( Connection connection = DriverManager.getConnection(url, properties); Statement statement = connection.createStatement(); ) {
		String sql = "SELECT * FROM person";
		ResultSet result = statement.executeQuery(sql);
		
		while(result.next()){
			String userid = result.getString("userid");
			String email = result.getString("email");
			String password = result.getString("password");
			String firstname = result.getString("firstname");
			String lastname = result.getString("lastname");
			person = new Person(userid, email, password, firstname, lastname);
			persons.add(person);
		}
		return persons;
		
		}catch (SQLException e) { throw new DbException(e.getMessage(), e); }	
	}
	
	@Override
	public void add(Person person) {
		if(person == null){ throw new DbException("Nothing to add."); }
		if(this.getAll().contains(person)){ throw new DbException("Person already exists."); }
		
		String sql = "INSERT INTO r0668325.person(userid, email, password, firstname, lastname)" + "VALUES(?,?,?,?,?)";
		
		try ( Connection connection = DriverManager.getConnection(url, properties); PreparedStatement statement = connection.prepareStatement(sql); ) {			
			statement.setString(1, person.getUserid());
			statement.setString(2, person.getEmail());
			statement.setString(3, person.getPassword());
			statement.setString(4, person.getFirstName());
			statement.setString(5, person.getLastName());		
			statement.execute();
		}catch (SQLException e) { throw new DbException(e.getMessage(), e); }	
	}

	@Override
	public void update(Person person) {
		if(person == null){ throw new DbException("Nothing to add."); }
		
		String sql = "UPDATE person SET email = " + "?" + ", password = " + "?" + ", firstname = " + "?" + ", lastname = " + "?" + " where userid = " + "?";
		try ( Connection connection = DriverManager.getConnection(url, properties); PreparedStatement statement = connection.prepareStatement(sql) ) {			
			statement.setString(1, person.getUserid());
			statement.setString(2, person.getEmail());
			statement.setString(3, person.getPassword());
			statement.setString(4, person.getFirstName());
			statement.setString(5, person.getLastName());	
			statement.execute(sql);
		}catch (SQLException e) { throw new DbException(e.getMessage(), e); }
	}

	@Override
	public void delete(String personId) {
		if(personId == null || personId.trim().isEmpty()){ throw new DbException("Give a valid personID");	}
		
		String sql = "DELETE FROM person WHERE userid = ? ";
		try ( Connection connection = DriverManager.getConnection(url, properties); PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, personId);
			statement.execute();
		}catch (SQLException e) { throw new DbException(e.getMessage(), e); }
	}
}
