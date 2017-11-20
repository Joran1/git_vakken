package ui;

import domain.Employee;
import domain.observer.Observer;
import domain.observer.ObserverLogger;
import domain.observer.ObserverMailer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class View extends GridPane  {
	private TextField nameField;
	private Label messageLabel;
	
	public View() {
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(10, 10, 10, 10));

		messageLabel = new Label("No new collegues...");
		add(messageLabel, 0, 0);
		setColumnSpan(messageLabel, 2);
		
		Text nameLabel = new Text("Name: ");
		add(nameLabel, 0, 1);
		nameField = new TextField();
		add(nameField, 1, 1);
		
		Button buttonCancel = new Button("Cancel");
		buttonCancel.setOnAction(new CancelHandler());
		add(buttonCancel, 0, 3);
		Button buttonOk = new Button("Save");
		add(buttonOk, 1, 3);
		buttonOk.setOnAction(new NewEmployeeHandler());
	}

	class NewEmployeeHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			String name = nameField.getText();
			Employee employee = new Employee(name);
			Observer log = new ObserverLogger(employee);
			Observer mail = new ObserverMailer(employee);
			employee.notifyObserver();
			nameField.setText("");
			messageLabel.setText(employee.getName() + " added as new employee.");
		}
	}

	class CancelHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			System.exit(0);
		}
	}
}
