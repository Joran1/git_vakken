package domain;

import java.util.ArrayList;
import java.util.List;
import domain.observer.*;

public class Employee implements Subject{
	private int id;
	private String name;

	private List<Observer> observers = new ArrayList<Observer>();
	
	public Employee(String name) {
		this(0, name);
	}

	public Employee(int id, String name) {
		setId(id);
		setName(name);
	}

	//GETTERS EN SETTERS
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getName() { return name; }
	private void setName(String name) { this.name = name; }
		
	//OBSERVER	
	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObserver() {
		for(Observer observer : observers) {
			observer.update();
		}
	}
		
	//TO STRING
	public String toString(){ return getName(); }
	
}