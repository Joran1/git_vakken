package domain;

import java.util.List;
import java.util.Properties;

import db.PersonDb;
import db.PersonDbSQL;
import db.ProductDb;
import db.ProductDbSQL;

public class ShopService {
	private PersonDb personDb;
	private ProductDb productDb;

	 public ShopService(Properties properties) {
		 personDb = new PersonDbSQL(properties);
		 productDb = new ProductDbSQL(properties);
	}
	
	//getters
	public Person getPerson(String personId) {
		return getPersonDb().get(personId);
	}	
	public Product getProduct(int productId) {
		return getProductDb().get(productId);
	}

	//getters all
	public List<Person> getPersons() {
		return getPersonDb().getAll();
	}
	public List<Product> getProducts() {
		return getProductDb().getAll();
	}

	//add
	public void addPerson(Person person) {
		getPersonDb().add(person);
	}
	public void addProduct(Product product) {
		getProductDb().add(product);
	}

	//update
	public void updatePersons(Person person) {
		getPersonDb().update(person);
	}
	public void updateProducts(Product product) {
		getProductDb().update(product);
	}

	//delete
	public void deletePerson(String id) {
		getPersonDb().delete(id);
	}
	public void deleteProduct(int id) {
		getProductDb().delete(id);
	}

	//getDB
	private PersonDb getPersonDb() {
		return personDb;
	}
	private ProductDb getProductDb() {
		return productDb;
	}
}
