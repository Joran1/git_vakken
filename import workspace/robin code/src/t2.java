package ui;

import application.YahtzeeAppGame;
import domain.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A class of ...
 *
 * @author  ...
 * @version 1.0
 */
public class View extends GridPane{
	private TextField nameField;
	private Label messageLabel;
	private Button buttonCancel;
	public View() {
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(10, 10, 10, 10));

		messageLabel = new Label("What's your name?");
		add(messageLabel, 0, 0);
		setColumnSpan(messageLabel, 2);
		
		Text nameLabel = new Text("Name: ");
		add(nameLabel, 0, 1);
		nameField = new TextField();
		add(nameField, 1, 1);
		
		buttonCancel = new Button("Cancel");
		buttonCancel.setOnAction(new CancelHandler());
		add(buttonCancel, 0, 3);
		
		Button buttonSave = new Button("Save Player");
		buttonSave.setOnAction(new SaveHandler());
		
	}

	class CancelHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			
			GameView game = new GameView();
			new YahtzeeAppGame().start(new Stage());
			buttonCancel.getScene().getWindow().hide();
		}
	}


	class SaveHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			Player player = new Player(nameField.getText());
		}
		
		
	}
}