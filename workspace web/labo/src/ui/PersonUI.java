package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

import domain.DomainException;
import domain.Person;

public class PersonUI {

	public static void main(String[] args) throws ClassNotFoundException {
		
		Properties properties = new Properties();
		String url = "jdbc:postgresql://gegevensbanken.khleuven.be:51617/1TX35?" + "currenSchema=r0668325";
		
		properties.setProperty("user", "r0668325");
		properties.setProperty("password", "Jo311098");
		properties.setProperty("ssl", "true");
		properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
		
		String id = JOptionPane.showInputDialog("UserId?");
		String em = JOptionPane.showInputDialog("Email?");
		String pw = JOptionPane.showInputDialog("Password?");
		String fn = JOptionPane.showInputDialog("First name?");
		String ln = JOptionPane.showInputDialog("Last name?");
		
		try { Person addedPerson = new Person(id, em, pw, fn, ln); System.out.println(addedPerson); } catch (IllegalArgumentException d) { System.out.println("ERROR: could not create new person with those parameters"); }
		Class.forName("org.postgresql.Driver");
		Connection connection;
		
		try {
		connection = DriverManager.getConnection(url,properties);
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery( "SELECT * FROM person" );
		
		while(result.next()){
			
			String userid = result.getString("userid");
			String email = result.getString("email");
			String password = result.getString("password");
			String firstname = result.getString("firstname");
			String lastname = result.getString("lastname");
		
			Person person = new Person(userid, email, password, firstname, lastname);
			System.out.println(person);
		}
			
		statement.close();
		connection.close();
		} catch (SQLException e) { e.printStackTrace();	}
	}
}