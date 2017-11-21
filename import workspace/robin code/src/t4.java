package application;

import ui.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class YahtzeeAppGame extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		GameView game = new GameView();
		
		Scene mainScene = new Scene(game);
		primaryStage.setTitle("Yahtzee"); 
        primaryStage.setScene(mainScene); 
        primaryStage.show();
	}
}