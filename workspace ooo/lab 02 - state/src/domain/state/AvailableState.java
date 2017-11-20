package domain.state;

import domain.Product;

public class AvailableState implements State{
	
	Product product;
	
	public AvailableState(Product product) {
		this.product = product;
	}

	@Override
	public void rent() {
		product.setState(product.getRentedState());
	}

	@Override
	public void bringback(boolean damaged) {
		
	}

	@Override
	public void repair() {
		
	}

	@Override
	public void remove() {
		product.setState(product.getRemovedState());
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public String toString() {
		return "Available";
	}

}
