package domain.state;

import domain.Product;

public class DamagedState implements State{
	
	Product product;
	
	public DamagedState(Product product) {
		this.product = product;
	}

	@Override
	public void rent() {
		
	}

	@Override
	public void bringback(boolean damaged) {
		
	}

	@Override
	public void repair() {
		product.setState(product.getAvailableState());
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
		return "Damaged";
	}

}
