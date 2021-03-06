package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.Product;

public class ProductDbSQL implements ProductDb{

	private Properties properties;
	private String url;
	
	public ProductDbSQL(Properties properties) {
		this.properties = properties;
		this.url = properties.getProperty("url");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			throw new DbException(e.getMessage(), e);
		}
	}
	
	@Override
	public Product get(int id) {
		if(id < 0){
			throw new DbException("Give a valid id");
			}
		Product product = null;
		try (
				Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.createStatement()
				) {
		String sql = "SELECT * FROM product WHERE productid="+ id;
		ResultSet result = statement.executeQuery(sql);
		while(result.next()){
			String productid = result.getString("productid");
			String name = result.getString("name");
			String description = result.getString("description");
			String price = result.getString("price");
			product = new Product(Integer.parseInt(productid), name, description, Double.parseDouble(price));
		}
		
		}catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
		if(product == null){
			throw new DbException("Geef een geldig productid.");
		}else{
		return product;
		}
	}

	@Override
	public List<Product> getAll() {
		List<Product> products = null;
		Product product = null;
		
		try (
				Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.createStatement()
				) {
		String sql = "SELECT * FROM r0668325.product";
		ResultSet result = statement.executeQuery(sql);
		products = new ArrayList<>();
		while(result.next()){
			String productid = result.getString("productid");
			String name = result.getString("name");
			String description = result.getString("description");
			String price = result.getString("price");			
			product = new Product(Integer.parseInt(productid), name, description, Double.parseDouble(price));
		
			products.add(product);

		}
		
		}catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
		return products;

	}

	@Override
	public void add(Product product) {
		if(product == null){
			throw new DbException("Nothing to add.");
			}
		if(this.getAll().contains(product)){
			throw new DbException("Person already exists.");

		}
		try (
				Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.createStatement()
				) {

		String name = product.getName();
		String description = product.getDescription();
		Double price = product.getPrice();
		
		String sql = "insert into product(name, description, price) "
				+ "values('"+ name +"', '"+description+"', '"+price+"')";
		statement.executeUpdate(sql);
		}catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	@Override
	public void update(Product product) {
		if(product == null){
			throw new DbException("Nothing to add.");
			}
		try (Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.createStatement();) {
			statement.execute("UPDATE r0668325.product SET name = '" + product.getName() + "', description ='"
					+ product.getDescription() + "', price =" + product.getPrice() + "WHERE productid ="+product.getProductId() );
		} catch (SQLException E) {
			throw new DbException(E.getMessage(), E);
		}	
	}

	@Override
	public void delete(int id) {
		if(id < 0){
			throw new DbException("Give a valid personID");
			}
		try (
				Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.createStatement()
				) {
			
			String sql = "DELETE FROM product WHERE productid = '" + id + "'; ";
			statement.executeUpdate(sql);
		}catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}		
	}

}
