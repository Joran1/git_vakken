package domain.state;

public class RemovedState implements State{

	public RemovedState() {		
	}

	@Override
	public void rent() {
		
	}

	@Override
	public void bringback(boolean damaged) {
		
	}

	@Override
	public void repair() {
		
	}

	@Override
	public void remove() {
		
	}
	
	@Override
	public String toString() {
		return "Removed";
	}

}
