package edu.orangecoastcollege.cs272.p04.bookstore.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.p04.bookstore.controller.Controller;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Customer;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Order;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * <code>Order1AddCustomerScene</code> the 1st scene of the Create Order process.
 * To add the customer to the order, this is the customer who placed the order.
 * User may choose from list of existing customers, or directly add a new customer.
 * "Continue" button will record the customer object AND create a new BLANK order row in the database ONLY.
 * this blank order will either be discarded, if the order process is not completed,
 * or the blank order will be updated in the final Create Order scene.
 * 
 * @author Danny Hsu
 * @version 1.0
 */
public class Order1AddCustomerScene extends NavigationBar implements Initializable
{

	@FXML
	private Button addNewCustomerButton;
	
	@FXML
	private Button continueButton;
	
	private static Controller controller = Controller.getInstance();

	@FXML
	private TableView<Customer> customerTV;
	
	ObservableList<Customer> customerList = controller.getAllCustomers();
	
	@FXML
	private TextField customerNameTF;
	
	@FXML
	private TextField addressTF;
	
	@FXML
	private TextField phoneTF;
	
	@FXML
	private TextField emailTF;

	// Customer to add to order
	Customer selectedCustomer;

	Order thisOrder;

	
	
	/**
	 * continueButtonAbility() method determine if the Continue Button is usable
	 */
	public void continueButtonAbility()
	{
		// Enable the Continue Button only when a Customer is selected
		if (selectedCustomer == null)
		{
			continueButton.setDisable(true);

		}
		else
			continueButton.setDisable(false);		
	}
	
	
	
	/**
	 * initialize method initialized the scene with nodes and methods
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		customerTV.setItems(customerList);
		TableColumn<Customer, String> nameCol = new TableColumn<Customer, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		TableColumn<Customer, String> addressCol = new TableColumn<Customer, String>("Address");
		addressCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
		TableColumn<Customer, String> phoneCol = new TableColumn<Customer, String>("Phone");
		phoneCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
		TableColumn<Customer, String> emailCol = new TableColumn<Customer, String>("Email");
		emailCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
		customerTV.getColumns().setAll(nameCol, addressCol, phoneCol, emailCol);
		
		customerTV.setOnMouseClicked(e->selectedCustomerAction());
	}
	
	
	/**
	 * selectedCustomerAction() method select the current customer object
	 * so we can pass on the info the the next scene
	 */
	public void selectedCustomerAction()
	{
		selectedCustomer = customerTV.getSelectionModel().getSelectedItem();
		
		continueButtonAbility();
		
		System.out.println(selectedCustomer); //test
	}
	
	
	/**
	 * addNewCustomerAction() method is used to add a new customer during the Create Order process
	 * so we do not have to leave here just to add a new customer in the Add New Customer Scene
	 * 
	 * Information is passed by Controller to the update Customer database
	 * 
	 *Once the New Customer is added, it is automatically selected as the 
	 * Customer (and its associated ID) to be linked with the Order
	 * @return
	 */
	@FXML
	public Object addNewCustomerAction()
	{
		String name = customerNameTF.getText();
		String address = addressTF.getText();
		String phone = phoneTF.getText();
		String email = emailTF.getText();		
		
		// Name field is required
		if (customerNameTF.getText().isEmpty())
		{
			customerNameTF.setPromptText("Required");
			customerNameTF.setStyle("-fx-prompt-text-fill: red;");
			return null;
		}
		
		customerNameTF.setPromptText("Name");
		customerNameTF.setStyle("-fx-prompt-text-fill: gray;");
		customerNameTF.clear();
		addressTF.clear();
		phoneTF.clear();
		emailTF.clear();
		
		String result = controller.addCustomerControl(name, address, phone, email);
		
		if (result.equalsIgnoreCase("SUCCESS"))
		{

			// Select the newest added customer as my current customer
			customerTV.requestFocus();
			customerTV.getSelectionModel().select(customerList.size()-1);
			customerTV.getFocusModel().focus(customerList.size()-1);
			customerTV.scrollTo(customerList.size()-1);
			selectedCustomerAction();
			
			
			//test
			for (Customer a : customerList)
			{
				System.out.println(a.toString());
			}			
		}
		
		return this;
	}
	
	/**
	 * continueAction() method continue to scene (2) of the Create Order process
	 * 
	 * This will create a new order row in the database table, so we have access to Order ID
	 */
	@FXML
	public void continueAction() 
	{
		// Capture this Customer
		controller.setCurrentCustomer(selectedCustomer);
		
		// Create New Order Row in DB
		controller.createNewEmptyOrderControl();
		
		returnOrder2AddBook();
	}

	



}