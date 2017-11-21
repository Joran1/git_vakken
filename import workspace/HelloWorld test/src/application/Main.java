package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
	try {		
		Button btn = new Button();
		btn.setOnAction(new EventHandler<ActionEvent>() {			 
			@Override
				public void handle(ActionEvent event) {
		        	System.out.println("test");
			}
		});
		btn.setText("test");
			
		StackPane root = new StackPane();
		root.getChildren().add(btn);
		
		primaryStage.setScene(new Scene(root,300,100));
		primaryStage.setTitle("test");
		primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
