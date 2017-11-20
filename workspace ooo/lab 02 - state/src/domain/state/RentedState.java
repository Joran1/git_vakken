package domain.state;

import domain.Product;

public class RentedState implements State {
	
	Product product;
	
	public RentedState(Product product) {
		this.product = product;
	}

	@Override
	public void rent() {
		
	}

	@Override
	public void bringback(boolean damaged) {
		if(damaged) { product.setState(product.getDamagedState()); } else { product.setState(product.getAvailableState()); }
	}

	@Override
	public void repair() {
		
	}

	@Override
	public void remove() {
		
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public String toString() {
		return "Rented";
	}

}
