package edu.orangecoastcollege.cs272.p04.bookstore.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.p04.bookstore.controller.Controller;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Customer;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * <code>ManageCustomersScene</code> GUI for managing the Customers in our
 * customer contact list part of the system.
 * User may Add new Customer (via Add Customer Scene) and edit the existing
 * customers (via the TableView)
 * @author Danny Hsu
 * @version 1.0
 */
public class ManageCustomersScene extends NavigationBar implements Initializable
{

	@FXML
	private Button addCustomerButton;
	
	@FXML
	private TableView<Customer> customerTV;
	
	private static Controller controller = Controller.getInstance();

	ObservableList<Customer> customerList = controller.getAllCustomers();
	
	// selected Customer
	Customer selectedCustomerToEdit;
	
	private int selectedCustomerID() 
	{
		selectedCustomerToEdit = customerTV.getSelectionModel().getSelectedItem();
		System.out.println(selectedCustomerToEdit);
		return selectedCustomerToEdit.getId();
	}

	/**
	 * initialize method initialized the scene with nodes and methods
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		customerTV.setOnMouseClicked(e-> selectedCustomerID());

		customerTV.setItems(customerList);
		customerTV.setEditable(true);

		TableColumn<Customer, String> nameCol = new TableColumn<Customer, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		
		nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		nameCol.setOnEditCommit(
				new EventHandler<CellEditEvent<Customer, String>>(){
					@Override
					public void handle(CellEditEvent<Customer, String> c){
						controller.updateCustomerNameControl(selectedCustomerID(), c.getOldValue(), c.getNewValue());						
						((Customer) c.getTableView().getItems().get(
		                        c.getTablePosition().getRow())
		                        ).setName(c.getNewValue());
					}
				}
				);
		TableColumn<Customer, String> addressCol = new TableColumn<Customer, String>("Address");
		addressCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
		
		addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
		addressCol.setOnEditCommit(
				new EventHandler<CellEditEvent<Customer, String>>(){
					@Override
					public void handle(CellEditEvent<Customer, String> c){
						controller.updateCustomerAddressControl(selectedCustomerID(), c.getOldValue(), c.getNewValue());						
						((Customer) c.getTableView().getItems().get(
		                        c.getTablePosition().getRow())
		                        ).setAddress(c.getNewValue());
					}
				}
				);
		TableColumn<Customer, String> phoneCol = new TableColumn<Customer, String>("Phone");
		phoneCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
		
		phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
		phoneCol.setOnEditCommit(
				new EventHandler<CellEditEvent<Customer, String>>(){
					@Override
					public void handle(CellEditEvent<Customer, String> c){
						controller.updateCustomerPhoneControl(selectedCustomerID(), c.getOldValue(), c.getNewValue());						
						((Customer) c.getTableView().getItems().get(
		                        c.getTablePosition().getRow())
		                        ).setPhone(c.getNewValue());
					}
				}
				);
		TableColumn<Customer, String> emailCol = new TableColumn<Customer, String>("Email");
		emailCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
		
		emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
		emailCol.setOnEditCommit(
				new EventHandler<CellEditEvent<Customer, String>>(){
					@Override
					public void handle(CellEditEvent<Customer, String> c){
						controller.updateCustomerEmailControl(selectedCustomerID(), c.getOldValue(), c.getNewValue());						
						((Customer) c.getTableView().getItems().get(
		                        c.getTablePosition().getRow())
		                        ).setEmail(c.getNewValue());
					}
				}
				);
		
		customerTV.getColumns().setAll(nameCol, addressCol, phoneCol, emailCol);
	}
	
	/**
	 * addCustomerAction() method is for the Add Customer button that
	 * leads to the Add Customer Scene
	 */
	public void addCustomerAction()
	{
		ViewNavigator.loadScene("Add Customer", ViewNavigator.ADD_CUSTOMER_SCENE);
	}
}
