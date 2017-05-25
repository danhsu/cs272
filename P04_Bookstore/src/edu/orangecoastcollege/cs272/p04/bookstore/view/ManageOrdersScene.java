package edu.orangecoastcollege.cs272.p04.bookstore.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.p04.bookstore.controller.Controller;
import edu.orangecoastcollege.cs272.p04.bookstore.model.OrderInformation;
import edu.orangecoastcollege.cs272.p04.bookstore.model.User;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Book;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Customer;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * <code>ManageOrdersScene</code> is the GUI scene for managing sales orders of the system.
 * It displays a list of orders, by All, by User Only, by Customer Only.
 * User may update the Order Status by double-clicking on the item.
 * 
 * @author Danny Hsu
 * @version 1.0
 */
public class ManageOrdersScene extends NavigationBar implements Initializable
{
	private static Controller controller = Controller.getInstance();

	@FXML
	private TableView<OrderInformation> orderTV;
	
	@FXML
	private TextArea booksInOrderTA;
	
	OrderInformation selectedOrder;

	ObservableList<Order> orderList = controller.getAllOrders();
	ObservableList<OrderInformation> mainList = FXCollections.observableArrayList();
	ObservableList<Customer> custList = controller.getAllCustomers();
	ObservableList<User> userList = controller.getAllUsers();
	ObservableList<Book> soldBookList = controller.getAllSoldBookList();
	
	@FXML
	private ComboBox<String> userListCB;
	@FXML
	private ComboBox<String> custListCB;
	ObservableList<String> userStringList = FXCollections.observableArrayList();
	ObservableList<String> customerStringList = FXCollections.observableArrayList();

	@FXML
	ToggleGroup group = new ToggleGroup();
	
	@FXML
	private RadioButton showAllRadioButton;
	@FXML
	private RadioButton userRadioButton;
	@FXML
	private RadioButton customerRadioButton;

	/**
	 * clearTV() is used to refresh the TableView
	 */
	public void clearTV()
	{
		for ( int i = 0; i< orderTV.getItems().size(); i++) {
			orderTV.getItems().clear();
		}
		
	}
	
	/**
	 * populateOrders() takes different ObservableList to display in the TableView
	 * Options will be deterine by RadioButton toggle
	 * (1) All Orders list
	 * (2) All Orders made by User List
	 * (3) All Orders made by Customer List
	 * @param displayList - the ObservableList to display in TableView
	 */
	public void populateOrders(ObservableList<Order> displayList)
	{
		int orderId = 0;
		String customer = null;
		String username = null;
		double total = 0;
		String orderTypeString = null;
		String orderStatusString = null;
		
		
		for (Order eachOrder : displayList)
		{
			orderId = eachOrder.getId();
			total = eachOrder.getTotal();
			
			if (eachOrder.isOrderType() == false)
			{
				orderTypeString = "In-Store Order";
			}
			if (eachOrder.isOrderType() == true)
			{
				orderTypeString = "Advance Order";
			}
			
			if (eachOrder.getOrderStatusId() == 1)
			{
				orderStatusString = "To Be Picked Up";
			}
			else if (eachOrder.getOrderStatusId() == 2)
			{
				orderStatusString = "To Be Shipped";
			}
			else if (eachOrder.getOrderStatusId() == 3)
			{
				orderStatusString = "Picked Up";
			}
			else if (eachOrder.getOrderStatusId() == 4)
			{
				orderStatusString = "Shipped";
			}
			else
			{
				orderStatusString = " - ";
			}
			
			for (User u : userList)
			{
				if (eachOrder.getUserId() == u.getId())
				{
					username = u.getUsername();
				}
			}
			
			for (Customer cust : custList)
			{
				if (eachOrder.getCustomerId() == cust.getId())
				{
					customer = cust.getName();
				}
			}
			
			ArrayList<String> booksInOrder = controller.getBookTitlesInOrder(orderId);

			OrderInformation myOrder = new OrderInformation(orderId, username, customer, total, orderTypeString, orderStatusString, booksInOrder);
			
			mainList.add(myOrder);	
		}
	}

	
	/**
	 * userListCBAction() compile a ObservableList of orders made by a selected User in ComboBox
	 */
	public void userListCBAction()
	{	
		clearTV();
		custListCB.getSelectionModel().clearSelection();
		
		ObservableList<Order> orderByUser = FXCollections.observableArrayList();

		for (User u : userList)
		{
			if (userListCB.getSelectionModel().getSelectedItem() == u.getUsername())
			{
				for (Order eachOrder : orderList)
				{
					if (eachOrder.getUserId() == u.getId())
					{
						orderByUser.add(eachOrder);
					}
				}
			}
		}
		populateOrders(orderByUser);
	}
	
	
	/**
	 * custListCBAction() compile a ObservableList of orders made by a selected Customer in ComboBox
	 */
	public void custListCBAction()
	{
		clearTV();
		userListCB.getSelectionModel().clearSelection();
		
		ObservableList<Order> orderByCustomer = FXCollections.observableArrayList();

		for (Customer c : custList)
		{
			if (custListCB.getSelectionModel().getSelectedItem() == c.getName())
			{
				for (Order eachOrder : orderList)
				{
					if (eachOrder.getCustomerId() == c.getId())
					{
						orderByCustomer.add(eachOrder);
					}
				}
			}
		}
		populateOrders(orderByCustomer);
	}
	
	
	/**
	 * resetAllAction() will refresh all elements on the Scene
	 */
	public void resetAllAction()
	{
		userListCB.getSelectionModel().clearSelection();
		custListCB.getSelectionModel().clearSelection();
		booksInOrderTA.clear();
		clearTV();
		populateOrders(orderList);

	}
	
	/**
	 * radioButtonAction() determine the group of RadioButton and their actions:
	 * Show All, Show Order By User, Show Order By Customer
	 * when one is selected, the other two cannot be used, so the TableView can display the correct list
	 */
	@FXML
	public void radioButtonAction()
	{
		if (showAllRadioButton.isSelected())
		{
			resetAllAction();
			userListCB.setDisable(true);
			custListCB.setDisable(true);

		}
		if (userRadioButton.isSelected())
		{
			resetAllAction();
			userListCB.setDisable(false);
			custListCB.setDisable(true);

		}
		if (customerRadioButton.isSelected())
		{
			resetAllAction();
			custListCB.setDisable(false);
			userListCB.setDisable(true);
		}
	}
	
	/**
	 * selectedOrderBooksInOrderTextAreaAction() display the selected Order's Books in Order in the TextArea
	 * Also used to capture the Order ID to be used to pass onto the Update Order Status TableView Cell Action.
	 * @return - the selected Order in the TableView's Order ID
	 */
	public int selectedOrderBooksInOrderTextAreaAction()
	{		
		selectedOrder = orderTV.getSelectionModel().getSelectedItem();
		
		ArrayList<String> bookslistArray = selectedOrder.getBookTitleList();
		
		booksInOrderTA.clear();
		
		for (String book : bookslistArray)
		{
			booksInOrderTA.appendText(book);
			booksInOrderTA.appendText("\n");
		}		
		
		System.out.println(selectedOrder);
		return selectedOrder.getOrderId();
	}
	
	
	/**
	 * initialize method initialized the scene with nodes and methods
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		orderTV.setOnMouseClicked(e -> selectedOrderBooksInOrderTextAreaAction());

		
		radioButtonAction();
		
		// associate COMBOBOX
		for (User u : userList)
		{
			userStringList.add(u.getUsername());
		}
		userListCB.setItems(userStringList);;
		
		for (Customer c : custList)
		{
			customerStringList.add(c.getName());
		}
		custListCB.setItems(customerStringList);
		
		userListCB.setOnAction(e -> userListCBAction());
		custListCB.setOnAction(e -> custListCBAction());
		
		// associate TABLEVIEW
		orderTV.setItems(mainList);
		orderTV.setEditable(true);

		TableColumn<OrderInformation, String> userCol = new TableColumn<OrderInformation, String>("Created By");
		userCol.setCellValueFactory(new PropertyValueFactory<OrderInformation, String>("username"));
		
		TableColumn<OrderInformation, String> custCol = new TableColumn<OrderInformation, String>("Customer");
		custCol.setCellValueFactory(new PropertyValueFactory<OrderInformation, String>("customer"));

		TableColumn<OrderInformation, String> totalCol = new TableColumn<OrderInformation, String>("Total $");
		totalCol.setCellValueFactory(new PropertyValueFactory<OrderInformation, String>("total"));

		TableColumn<OrderInformation, String> orderTypeCol = new TableColumn<OrderInformation, String>("Order Type");
		orderTypeCol.setCellValueFactory(new PropertyValueFactory<OrderInformation, String>("orderType"));
		
		TableColumn<OrderInformation, String> orderStatusCol = new TableColumn<OrderInformation, String>("Order Status");
		orderStatusCol.setCellValueFactory(new PropertyValueFactory<OrderInformation, String>("orderStatus"));
		// Order Status Column is the ONLY one editable!
		// Used to Update Order Status for the selected Order
		// Even though, by design, ONLY Advance Order can be updated, but in this currently implementation
		// based on our limited understanding of TableView, it does NOT know the difference between 
		// In-Store and Advance Order, so ALL selected Order's Order Status CAN be updated
		ObservableList<String> statusList = FXCollections.observableArrayList();
		statusList.add(" - ");
		statusList.add("To Be Picked Up");
		statusList.add("To Be Shipped");
		statusList.add("Picked Up");
		statusList.add("Shipped");
		orderStatusCol.setCellFactory(ComboBoxTableCell.forTableColumn(statusList));
		orderStatusCol.setOnEditCommit(
				new EventHandler<CellEditEvent<OrderInformation, String>>(){
					@Override
					public void handle(CellEditEvent<OrderInformation, String> order){
						controller.updateOrderStatusControl(selectedOrderBooksInOrderTextAreaAction(), order.getOldValue(), order.getNewValue());

						((OrderInformation) order.getTableView().getItems().get(
								order.getTablePosition().getRow())
			                        ).setOrderStatus(order.getNewValue());
						}
					}
				);
		
		orderTV.getColumns().setAll(userCol, custCol, totalCol, orderTypeCol, orderStatusCol);

		//TEST
		for (Order o : orderList)
		{
			System.out.println(o);
		}
	}
}
