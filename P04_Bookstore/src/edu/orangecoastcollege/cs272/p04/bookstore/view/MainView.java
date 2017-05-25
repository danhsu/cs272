package edu.orangecoastcollege.cs272.p04.bookstore.view;
 
import javafx.application.Application;
import javafx.stage.Stage;
 
/**
 * <code>MainView</code> represents the beginning
 * to the code that starts off the entire project.
 * @author Brian
 *
 */
public class MainView extends Application {
	
	/**
	 * startes off the project and launches it
	 * to the login scene
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewNavigator.setStage(primaryStage);
		
		ViewNavigator.loadScene("Welcome, please login", ViewNavigator.lOGIN_SCENE); 
 
	}
	
	/**
	 * Launches program
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
 
 
