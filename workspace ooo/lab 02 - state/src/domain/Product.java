package domain;

import domain.state.*;

public class Product {
	
	String id;
	String title;
	
	State AvailableState;
	State DamagedState;
	State RemovedState;
	State RentedState;
 
	State state = AvailableState;
	
	public Product(String id, String title) {
		setId(id);
		setTitle(title);
		
		AvailableState = new AvailableState(this);
		DamagedState = new DamagedState(this);
		RemovedState = new RemovedState();
		RentedState = new RentedState(this);
	}
	
	void rent() {
		state.rent();
	}
	
	void bringback(boolean damaged) {
		state.bringback(damaged);
	}
	
	void repair() {
		state.repair();
	}
	
	void remove() {
		state.remove();
	}

	//STATES
	public State getAvailableState() {
		return AvailableState;
	}
	public void setAvailableState(State availableState) {
		AvailableState = availableState;
	}

	public State getDamagedState() {
		return DamagedState;
	}
	public void setDamagedState(State damagedState) {
		DamagedState = damagedState;
	}

	public State getRemovedState() {
		return RemovedState;
	}
	public void setRemovedState(State removedState) {
		RemovedState = removedState;
	}

	public State getRentedState() {
		return RentedState;
	}
	public void setRentedState(State rentedState) {
		RentedState = rentedState;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	//GET SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
