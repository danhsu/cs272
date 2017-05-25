package edu.orangecoastcollege.cs272.p04.bookstore.view;
 
import java.net.URL;
import java.util.ResourceBundle;
 
import edu.orangecoastcollege.cs272.p04.bookstore.controller.Controller;
import edu.orangecoastcollege.cs272.p04.bookstore.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
 
/**
 * <code>ManageUsersScene</code> represents the scene where the admin
 * manages the users via adding in new users or editing existing ones.
 * When adding new users, the admin simply needs to click the 
 * addUserButton in order to be led to a new scene.  To edit, simply 
 * double-click the cell inside the table that which the admin wants 
 * to change and change it as prompted.
 * 
 * @author Brian
 */
public class ManageUsersScene extends NavigationBar implements Initializable
{
	
	@FXML
	private Button addUserButton;
	
	
	@FXML
	private TableView<User> userTV;
	
	@FXML
	private TableView<User> adminTV;
	
	private static Controller controller = Controller.getInstance();
 
	ObservableList<User> userList = controller.getAllUsers();
	ObservableList<User> userListAdmin = FXCollections.observableArrayList();
	ObservableList<User> userListNotAdmin = FXCollections.observableArrayList();
	
	User selectedUserToEdit;
	
	private int selectedUserID() 
	{
		selectedUserToEdit = userTV.getSelectionModel().getSelectedItem();
		System.out.println(selectedUserToEdit);
		return selectedUserToEdit.getId();
	}
	
	/**
	 * this sets up the table view of the entire scene.  we allowed 
	 * the table view to be editable by the admin.  However, the admin
	 * cannot change their own access level as that might lock them
	 * out of this menu forever. 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
 
		userTV.setOnMouseClicked(e-> selectedUserID());
		
		for (User u : userList)
		{
			System.out.println(u);
			if (u.getId() == 1)
			{
				userListAdmin.add(u);
			}
			else
			{
				userListNotAdmin.add(u);
			}
		}
		
		// Non-Admin Users TableView
		userTV.setItems(userListNotAdmin);
		userTV.setEditable(true);
		
		TableColumn<User, String> usernameCol = new TableColumn<User, String>("Username");
		usernameCol.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		
		usernameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		usernameCol.setOnEditCommit(
				new EventHandler<CellEditEvent<User, String>>(){
					@Override
					public void handle(CellEditEvent<User, String> u){
						controller.updateUsernameControl(selectedUserID(), u.getOldValue(), u.getNewValue());						
						((User) u.getTableView().getItems().get(
		                        u.getTablePosition().getRow())
		                        ).setUsername(u.getNewValue());
					}
				}
				);
		
		
		TableColumn<User, String> passwordCol = new TableColumn<User, String>("Password");
		passwordCol.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
		passwordCol.setCellFactory(TextFieldTableCell.forTableColumn());
		passwordCol.setOnEditCommit(
				new EventHandler<CellEditEvent<User, String>>(){
					@Override
					public void handle(CellEditEvent<User, String> u){
						controller.updatePasswordControl(selectedUserID(), u.getOldValue(), u.getNewValue());						
						((User) u.getTableView().getItems().get(
		                        u.getTablePosition().getRow())
		                        ).setPassword(u.getNewValue());
					}
				}
				);
		
		TableColumn<User, String> positionCol = new TableColumn<User, String>("Position");
		positionCol.setCellValueFactory(new PropertyValueFactory<User, String>("position"));
		positionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		positionCol.setOnEditCommit(
				new EventHandler<CellEditEvent<User, String>>(){
					@Override
					public void handle(CellEditEvent<User, String> u){
						controller.updatePositionControl(selectedUserID(), u.getOldValue(), u.getNewValue());
						((User) u.getTableView().getItems().get(
		                        u.getTablePosition().getRow())
		                        ).setPosition(u.getNewValue());
					}
				}
				);
		
 
		TableColumn<User, Integer> accessCol = new TableColumn<User, Integer>("Access Level");
		accessCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("accessLevel"));
 
		
		ObservableList<Integer> accessLevelSelections = FXCollections.observableArrayList();
		accessLevelSelections.add(2);
		accessLevelSelections.add(3);
		accessLevelSelections.add(4);
		accessCol.setCellFactory(ComboBoxTableCell.forTableColumn(accessLevelSelections));
		//accessCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
				
		accessCol.setOnEditCommit(
				new EventHandler<CellEditEvent<User, Integer>>(){
					@Override
					public void handle(CellEditEvent<User, Integer> u){
						String result = controller.updateAccessLevelControl(selectedUserID(), u.getOldValue(), u.getNewValue());
						if(!result.equalsIgnoreCase("Success")){
							return;
						}else{
						((User) u.getTableView().getItems().get(
		                        u.getTablePosition().getRow())
			                        ).setAccessLevel(u.getNewValue());
						}
						}
					}
				);
 
		userTV.getColumns().setAll(usernameCol, passwordCol, positionCol, accessCol);
		
		
		
		
		
		// Admin User Table View
		adminTV.setItems(userListAdmin);
		adminTV.setEditable(true);
 
		TableColumn<User, String> usernameCol2 = new TableColumn<User, String>("Username");
		usernameCol2.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		usernameCol2.setCellFactory(TextFieldTableCell.forTableColumn());
		usernameCol2.setOnEditCommit(
				new EventHandler<CellEditEvent<User, String>>(){
					@Override
					public void handle(CellEditEvent<User, String> u){
						controller.updateUsernameControl(1, u.getOldValue(), u.getNewValue());						
						((User) u.getTableView().getItems().get(
		                        u.getTablePosition().getRow())
		                        ).setUsername(u.getNewValue());
					}
				}
				);
		
		
		TableColumn<User, String> passwordCol2 = new TableColumn<User, String>("Password");
		passwordCol2.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
		passwordCol2.setCellFactory(TextFieldTableCell.forTableColumn());
		passwordCol2.setOnEditCommit(
				new EventHandler<CellEditEvent<User, String>>(){
					@Override
					public void handle(CellEditEvent<User, String> u){
						controller.updatePasswordControl(1, u.getOldValue(), u.getNewValue());						
						((User) u.getTableView().getItems().get(
		                        u.getTablePosition().getRow())
		                        ).setPassword(u.getNewValue());
					}
				}
				);
		
		TableColumn<User, String> positionCol2 = new TableColumn<User, String>("Position");
		positionCol2.setCellValueFactory(new PropertyValueFactory<User, String>("position"));
		positionCol2.setCellFactory(TextFieldTableCell.forTableColumn());
		positionCol2.setOnEditCommit(
				new EventHandler<CellEditEvent<User, String>>(){
					@Override
					public void handle(CellEditEvent<User, String> u){
						controller.updatePositionControl(1, u.getOldValue(), u.getNewValue());
						((User) u.getTableView().getItems().get(
		                        u.getTablePosition().getRow())
		                        ).setPosition(u.getNewValue());
					}
				}
				);
		
		TableColumn<User, String> accessCol2 = new TableColumn<User, String>("Access Level");
		accessCol2.setCellValueFactory(new PropertyValueFactory<User, String>("accessLevel"));
		
		accessCol2.setStyle("-fx-background-color:lightgray");
		
		adminTV.getColumns().setAll(usernameCol2, passwordCol2, positionCol2, accessCol2);
 
	}
 
	
 
 
	/**
	 * this just opens up another scene, add user,
	 * in order to continue the program
	 */
	public void addUserAction()
	{
		ViewNavigator.loadScene("Add User", ViewNavigator.ADD_USER_SCENE);
	}
 
 
}
 
 
 
