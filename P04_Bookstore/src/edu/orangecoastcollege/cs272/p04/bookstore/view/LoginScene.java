package edu.orangecoastcollege.cs272.p04.bookstore.view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.p04.bookstore.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * <code>LoginScene</code> represents the front page of the 
 * program.  It is the place where the user logs in to
 * adds to and/or edit from the database.  The login page
 * is for security purposes where the user can only
 * enter a perfect combination of the username and 
 * password.  Then and only then will the program will
 * allow the user access to the homepage accordingly
 * 
 * @author Brian
 */
public class LoginScene extends NavigationBar implements Initializable
{

	private static Controller controller = Controller.getInstance();
	
	@FXML
	private TextField usernameTF;
	
	@FXML
	private TextField passwordTF;
	
	@FXML
	private Label usernameErrorLabel;
	
	@FXML
	private Label passwordErrorLabel;
	
	@FXML
	private Label loginErrorLabel;
	
	/**
	 * This initializes the jazzy music.
	 * Badumtss.....
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String sound = "JazzMusic.mp3";
		Media music = new Media(new File(sound).toURI().toString());
		MediaPlayer mp = new MediaPlayer(music);
		mp.setCycleCount(MediaPlayer.INDEFINITE);
		mp.play();
	}
	
	/**
	 * This function is only supposed to make a scenerio where 
	 * when the function is activated, the username and password
	 * will be taken in and evaluated.  If the evaluation of
	 * the username and password doesnt match to anything in the DB
	 * then an error label is set to visible.  If it matches, then
	 * the user will be taken to the home page
	 * @return a null
	 */	
	@FXML
	public Object logIn() {
		usernameErrorLabel.setVisible(false);
		passwordErrorLabel.setVisible(false);
		loginErrorLabel.setVisible(false);
		
		String username = usernameTF.getText();
		String password = passwordTF.getText();
		
		if(username.isEmpty())usernameErrorLabel.setVisible(true);
		
		if(password.isEmpty())passwordErrorLabel.setVisible(true);
		
		if(usernameErrorLabel.isVisible() || passwordErrorLabel.isVisible())
			return null;
		
		
		String result = controller.logInUser(username, password);
		if(result.equalsIgnoreCase("success"))
			ViewNavigator.loadScene("Home Page", ViewNavigator.HOME_SCENE);
		else
			loginErrorLabel.setText(result);
			loginErrorLabel.setVisible(true);		
		
		return null;
	}
}
