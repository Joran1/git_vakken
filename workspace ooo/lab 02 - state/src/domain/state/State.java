package domain.state;

public interface State {

	void rent();
	void bringback(boolean damaged);
	void repair();
	void remove();
}
