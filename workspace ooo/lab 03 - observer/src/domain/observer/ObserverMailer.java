package domain.observer;

import domain.Employee;
import domain.Subject;

public class ObserverMailer implements Observer {
	
	private Employee e;
	
	public ObserverMailer(Employee employee) {
		this.e = employee;
		employee.addObserver(this);
	}
	
	@Override
	public void update() {
		System.out.println("Mail - " + e.getName() + " added as new employee.");
	}

}
