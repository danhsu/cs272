package edu.orangecoastcollege.cs272.p04.bookstore.view;
 
import java.net.URL;
import java.util.ResourceBundle;
 
import edu.orangecoastcollege.cs272.p04.bookstore.controller.Controller;
import edu.orangecoastcollege.cs272.p04.bookstore.model.AccessLevel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
 
/**
 * <code>AddUserScene</code> represents the scene where we add
 * in a new user.  The scene prompts the admin to add in a new
 * username, password, position, and access level (not admin
 * level).  However, the program will output an error name
 * if one field is not filled out.
 *  
 * @author Brian
 */
public class AddUserScene extends NavigationBar implements Initializable
{
 
	private static Controller controller = Controller.getInstance();
	
	@FXML
	private ComboBox<String> accessLevelCB;
	
	@FXML
	private TextField usernameTF;
 
	@FXML
	private TextField passwordTF;
	
	@FXML
	private TextField positionTF;
	
	@FXML
	private Label usernameErrorLabel;
	
	@FXML
	private Label passwordErrorLabel;
	
	@FXML
	private Label positionErrorLabel;
	
	@FXML
	private Label accessLvlErrorLabel;
	
	@FXML
	private Label saveErrorLabel;
	
	@FXML
	private Label badSaveErrorLabel;
	
	@FXML
	private Button cancelButton;
	
	@FXML
	private Button saveButton;
	
	
	ObservableList<AccessLevel> accessList = controller.getAllAccessLevels();
	ObservableList<String> accessString = FXCollections.observableArrayList();
 
	/**
	 * the method initializes any actions that needs to be ready
	 * to be done.  Essentially, the drop down menu is the action
	 * that needs to be ready.  Therefore, in this method, the 
	 * drop down menu is set up.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{		
	
		
		for (int i = 1; i < accessList.size(); i++)
		{
			String descString = accessList.get(i).getAccessLevel() + " - " + accessList.get(i).getDescription();
			accessString.add(descString);
		}
		accessLevelCB.setItems(accessString);
		
	}
	
	/**
	 * this method takes in the username, password, position and
	 * access level and tries to input it into the database.  The
	 * info is passed into the addUserControl from the controller
	 * class in order to validate the info.  If successful, the info
	 * is saved and you will be taken to the manage user scene, but if
	 * not, error labels will be passed out accordingly.
	 * @return a null
	 */
	@FXML
	public Object save(){
		usernameErrorLabel.setVisible(false);
		passwordErrorLabel.setVisible(false);
		positionErrorLabel.setVisible(false);
		accessLvlErrorLabel.setVisible(false);
		
		String username = usernameTF.getText();
		String password = passwordTF.getText();
		String position = positionTF.getText();
		int accessLvl = -1;
		//Integer.getInteger(accessLevelCB.getPromptText().substring(0, 1));
				
		if(username.isEmpty())
			usernameErrorLabel.setVisible(true);
		
		if(password.isEmpty())
			passwordErrorLabel.setVisible(true);
		
		if(position.isEmpty())
			positionErrorLabel.setVisible(true);
		
		if(accessLevelCB.getSelectionModel().getSelectedIndex() == -1)
			accessLvlErrorLabel.setVisible(true);
		else{
			switch (accessLevelCB.getSelectionModel().getSelectedItem()){
			case("2 - Assistant Manager"):{
				accessLvl = 2;
				break;
			}
			case("3 - Salesperson"):{
				accessLvl = 3;
				break;
			}
			case("4 - INACTIVE"):{
				accessLvl = 4;
				break;
			}
			default:
				break;
			}
		}
				
		if(usernameErrorLabel.isVisible() || passwordErrorLabel.isVisible() ||
				positionErrorLabel.isVisible() || accessLvlErrorLabel.isVisible())
			return null;
		
		String result = controller.addUserControl(username, password, position, accessLvl);
		if(result.equalsIgnoreCase("Success"))
			ViewNavigator.loadScene("Manage User", ViewNavigator.MANAGE_USERS_SCENE);
		else if(result.equalsIgnoreCase("same username error")){
			saveErrorLabel.setVisible(true);
		}
		else{
			badSaveErrorLabel.setVisible(true);
		}
		
		return null;
	}
	
	/**
	 * the cancels method takes us to the last scene.
	 * In this case, it is the manage user scene.
	 * @return a null
	 */
	@FXML
	public Object cancel(){
		ViewNavigator.loadScene("Manage User", ViewNavigator.MANAGE_USERS_SCENE);
		return null;
	}
	
}
 
 
