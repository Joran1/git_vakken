package domain.observer;

import domain.Employee;
import domain.Subject;

public class ObserverLogger implements Observer {
	
	private Employee e;
	
	public ObserverLogger(Employee employee) {
		this.e = employee;
		employee.addObserver(this);
	}
	
	@Override
	public void update() {
		System.out.println(e.getName() + " added as new employee.");
	}

}
