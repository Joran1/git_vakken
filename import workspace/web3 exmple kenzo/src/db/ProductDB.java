package db;

import java.util.List;

import domain.Product;

public interface ProductDB {
	Product get(int id);
	List<Product> getAll();
	void add(Product p);
	void update(Product p);
	void delete(int id);
}
