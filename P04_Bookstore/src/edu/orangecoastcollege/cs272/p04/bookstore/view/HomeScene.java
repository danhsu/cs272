package edu.orangecoastcollege.cs272.p04.bookstore.view;
 
import java.net.URL;
import java.util.ResourceBundle;
 
import edu.orangecoastcollege.cs272.p04.bookstore.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
 
 
/**
 * <code>HomeScene</code> represents the menu page for user.
 * Depending on their access level certain buttons will only 
 * be visible.  Of course, the admin will get to see all the
 * buttons, while the commoner users will see the three basic
 * buttons.
 * 
 * @author Brian
 */
public class HomeScene extends NavigationBar implements Initializable
{
	public Controller controller = Controller.getInstance();
	
	@FXML
	private Button createOrderButton;
	
	@FXML
	private Button manageCustomersButton;
 
	@FXML
	private Button manageOrdersButton;
	
	@FXML
	private Button bookInventoryButton;
	
	@FXML
	private Button manageUsersButton;
	
	@FXML
	private Label currentUserLabel;
	
	/**
	 * this method checks who the user is.  Depending on the access
	 * level of the user, certain buttons is set to visible.
	 * If the user is an administrator then they will be able
	 * to see all the buttons.  If the user is the manager, 
	 * then they will be able to see everything else but the
	 * manageUsers button
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
				
		currentUserLabel.setText(controller.getCurrentUser().getUsername());
		if(controller.getCurrentUser().getAccessLevel() == 1){
			bookInventoryButton.setVisible(true);
			manageUsersButton.setVisible(true);
		}else if(controller.getCurrentUser().getAccessLevel() == 2){
			bookInventoryButton.setVisible(true);
		}
		
		controller.deleteInvalidOrder();
	}
	
		
}
 
 
 
