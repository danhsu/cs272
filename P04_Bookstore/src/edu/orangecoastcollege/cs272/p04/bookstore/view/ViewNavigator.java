package edu.orangecoastcollege.cs272.p04.bookstore.view;
 
import java.io.IOException;
 
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
/**
 * Connects the code to the specific 
 * fxml file it wants to lead into afterwards 
 * @author Brian
 *
 */
public class ViewNavigator {
	
	/**
	 * create an a static final 
	 * variable for the specific 
	 * fxml file name
	 */
	public static final String ADD_AUTHOR_SCENE = "AddAuthorScene.fxml";
	public static final String ADD_BOOK_SCENE = "AddBookScene.fxml";
	public static final String ADD_CONDITION_SCENE = "AddConditionScene.fxml";
	public static final String ADD_CUSTOMER_SCENE = "AddCustomerScene.fxml";
	public static final String ADD_GENRE_SCENE = "AddGenreScene.fxml";
	public static final String ADD_USER_SCENE = "AddUserScene.fxml";
	public static final String BOOK_INVENTORY_SCENE = "BookInventoryScene.fxml";
	public static final String BOOK_PREVIEW_SCENE = "BookPreviewScene.fxml";
	public static final String HOME_SCENE = "HomeScene.fxml";
	public static final String lOGIN_SCENE = "LoginScene.fxml";
	public static final String MANAGE_CUSTOMERS_SCENE = "ManageCustomersScene.fxml";
	public static final String MANAGE_ORDERS_SCENE = "ManageOrdersScene.fxml";
	public static final String MANAGE_TABLES_SCENE = "ManageTablesScene.fxml";
	public static final String MANAGE_USERS_SCENE = "ManageUsersScene.fxml";
	
	public static final String CREATE_ORDER_SCENE = "CreateOrderScene.fxml";
	public static final String ORDER_1_ADD_CUSTOMER = "Order1AddCustomerScene.fxml";
	public static final String ORDER_2_ADD_BOOK = "Order2AddBookScene.fxml";
	public static final String ORDER_3_FINAL_ORDER = "Order3FinalScene.fxml";
	public static final String ORDER_4_EDIT_ORDER = "Order4EditOrderScene.fxml";
 
 
	/**
	 * Mainstage stage declaration
	 */
	public static Stage mainStage;
 
	/**
	 * sets the stage
	 * @param stage
	 */
	public static void setStage(Stage stage) {
		mainStage = stage;
	}
 
	/**
	 * allows the scene called to be loaded and 
	 * shown as the main stage.
	 * @param title
	 * @param sceneFXML
	 */
	public static void loadScene(String title, String sceneFXML) {
 
		try {
			mainStage.setTitle(title);
			Scene scene = new Scene(FXMLLoader.load(ViewNavigator.class.getResource(sceneFXML)));
			mainStage.setScene(scene);
			mainStage.show();
		} catch (IOException e) {
			System.err.println("Error loading: " + sceneFXML + "\n" + e.getMessage());
			e.printStackTrace();
		}
	}
 
}
 
 
 
