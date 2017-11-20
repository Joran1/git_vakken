package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import domain.Product;

public class ProductUI {

	public static void main(String[] args) throws ClassNotFoundException {
		
		Properties properties = new Properties();
		String url = "jdbc:postgresql://gegevensbanken.khleuven.be:51617/1TX35?" + "currenSchema=r0668325";
		
		properties.setProperty("user", "r0668325");
		properties.setProperty("password", "Jo311098");
		properties.setProperty("ssl", "true");
		properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
		
		Class.forName("org.postgresql.Driver");
		Connection connection;
		
		try {
		connection = DriverManager.getConnection(url,properties);
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery( "SELECT * FROM product" );
		
		while(result.next()){
			
			String name = result.getString("name");
			String description = result.getString("description");
			double price = Double.parseDouble(result.getString("price"));
		
			Product product = new Product(name, description, price);
			System.out.println(product);
		}
		
		statement.close();
		connection.close();
		} catch (SQLException e) { e.printStackTrace();	}
	}
}