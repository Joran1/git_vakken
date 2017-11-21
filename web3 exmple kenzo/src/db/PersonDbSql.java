package db;

import java.util.ArrayList;  
import java.util.List;
import java.util.Properties;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domain.Person;
import domain.Product;
import domain.Role;

public class PersonDbSql implements PersonDB {
	private Properties properties = new Properties();
	private String url;
	private Connection connection;
	private Statement statement;
	
	public PersonDbSql(Properties properties) {
		try {
			Class.forName("org.postgresql.Driver");
			this.properties = properties;
			this.url = properties.getProperty("url");
		} catch (ClassNotFoundException e) {
			throw new DbException();
		}
		
		try {
			connection = DriverManager.getConnection(url, properties);
		} catch (SQLException e) {
			throw new DbException();
		}
		
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			throw new DbException();
		}
	}
	
	@Override
	public Person get(String personId) {
		ResultSet result = null;
		PreparedStatement statement = null;
		Person person = null;
		String sql = "SELECT * FROM person WHERE id=?";

		try {
			statement = connection.prepareStatement(sql);
		} catch (SQLException e1) {
			throw new DbException("get person: PreparedStatement failed");
		}
		
		try {
			statement.setInt(1, Integer.parseInt(personId));
		} catch (SQLException e1) {
			throw new DbException("get person: setInt 1 => id failed");
		}
		
		try {
			result = statement.executeQuery();
		} catch (SQLException e1) {
			throw new DbException("get person: executeQuery failed " + e1.toString());
		}
		
		try {
			if(result.next()){
				String id = result.getString("id");
				String email = result.getString("email");
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				String password = result.getString("password");
				String strRole = result.getString("role").toUpperCase();
				Role role = Role.valueOf(strRole);
			
				person = new Person(id, email, password, firstName, lastName, role);
			} 
		} catch (NumberFormatException e) {
			throw new DbException();
		} catch (SQLException e) {
			throw new DbException();
		}	
		
		return person;
	}

	@Override
	public List<Person> getAll() {
		List<Person> personList = new ArrayList<>();
		ResultSet result = null;
		
		try {
			result = statement.executeQuery("SELECT * FROM person");
		} catch (SQLException e) {
			throw new DbException();
		}
		
		try {
			while(result.next()){
				String id = result.getString("id");
				String email = result.getString("email");
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				String password = result.getString("password");
				String salt = result.getString("salt");
				String strRole = result.getString("role").toUpperCase();
				Role role = Role.valueOf(strRole);

				Person p = new Person(id, email, password, firstName, lastName, role);
				p.setSalt(salt);
				personList.add(p);
			}
		} catch (SQLException e) {
			throw new DbException();
		}
		
		return personList;
	}

	@Override
	public void add(Person p) {
		PreparedStatement statement = null;
		String sql = "INSERT INTO person( Email, firstName, lastName, password, salt, role) VALUES (?,?,?,?,?,?)";
		
		try {
			statement = connection.prepareStatement(sql);
		} catch (SQLException e1) {
			throw new DbException("add product: PreparedStatement failed");
		}
		
		try {
			statement.setString(1, p.getEmail());
		} catch (SQLException e1) {
			throw new DbException("add person: setString 1 => p.getName() failed");
		}
		
		try {
			statement.setString(2, p.getFirstName());
		} catch (SQLException e1) {
			throw new DbException("add person: setString 2 => p.getDescription() failed");
		}
		
		try {
			statement.setString(3, p.getLastName());
		} catch (SQLException e1) {
			throw new DbException("add person: setDouble 3 => p.getLastName() failed");
		}
		
		try {
			statement.setString(4, p.getPassword());
		} catch (SQLException e1) {
			throw new DbException("add person: setDouble 3 => p.getPassword() failed");
		}
		
		try {
			statement.setString(5, p.getSalt());
		} catch (SQLException e1) {
			throw new DbException("add person: setDouble 3 => p.getSalt() failed");
		}
		
		try {
			statement.setString(6, p.getRole().toString());
		} catch (SQLException e1) {
			throw new DbException("add person: setString 6 => p.getRole failed");
		}
		
		try {
			statement.execute();
		} catch (SQLException e1) {
			throw new DbException("add person: execute failed " + e1.toString());
		}
	}

	@Override
	public void update(Person p) {
		PreparedStatement statement = null;
		String sql = "UPDATE PERSON set email=?, firstName=?, lastName=?, password=? WHERE id=?";
		
		try {
			statement = connection.prepareStatement(sql);
		} catch (SQLException e1) {
			throw new DbException("add person: PreparedStatement failed");
		}
		
		try {
			statement.setString(1, p.getEmail());
		} catch (SQLException e1) {
			throw new DbException("add person: setString 1 => p.getName() failed");
		}
		
		try {
			statement.setString(2, p.getFirstName());
		} catch (SQLException e1) {
			throw new DbException("add person: setString 2 => p.getDescription() failed");
		}
		
		try {
			statement.setString(3, p.getLastName());
		} catch (SQLException e1) {
			throw new DbException("add person: setDouble 3 => p.getPrice() failed");
		}
		
		try {
			statement.setString(4, p.getPassword());
		} catch (SQLException e1) {
			throw new DbException("add person: setDouble 3 => p.getPrice() failed");
		}
		
		try {
			statement.setString(5, p.getUserid());
		} catch (SQLException e1) {
			throw new DbException("add person: setDouble 3 => p.getPrice() failed");
		}
		
		try {
			statement.executeUpdate();
		} catch (SQLException e1) {
			throw new DbException("add person: execute failed " + e1.toString());
		}
	}

	@Override
	public void delete(String personId) {
		PreparedStatement statement = null;
		String sql = "DELETE FROM person WHERE id=?";
		
		try {
			statement = connection.prepareStatement(sql);
		} catch (SQLException e1) {
			throw new DbException("get person: PreparedStatement failed");
		}
		
		try {
			statement.setInt(1, Integer.parseInt(personId));
		} catch (SQLException e1) {
			throw new DbException("get person: setInt 1 => id failed");
		}
		
		try {
			statement.execute();
		} catch (SQLException e1) {
			throw new DbException("get person: execute failed");
		}
	}
	
	public void destroy(){
		try {
			statement.close();
		} catch (SQLException e) {
			throw new DbException();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			throw new DbException();
		}
	}
}
