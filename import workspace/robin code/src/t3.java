package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.View;

public class YahtzeeAppPlayerAdd extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		View view = new View();
		
		Scene mainScene = new Scene(view);
		primaryStage.setTitle("Yahtzee"); 
        primaryStage.setScene(mainScene); 
        primaryStage.show();
	}
}