package domain;

import domain.observer.Observer;

public interface Subject {
	
	public void addObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObserver();

}
