package edu.orangecoastcollege.cs272.p04.bookstore.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.p04.bookstore.controller.Controller;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Book;
import edu.orangecoastcollege.cs272.p04.bookstore.model.BookInformation;
import edu.orangecoastcollege.cs272.p04.bookstore.model.OrderStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * <code>Order3FinalScene</code> is the final scene of the Create Order process.
 * User review the order details.
 * Displays: customer name, user's user name, list of books in this order.
 * Use Radio Button to choose whether the book is In-Store Order or Advance Order.
 * if it is In-Store, there is no additional options.
 * if it is Advance, ComboBox appears, use it to select Order Status.
 * "Save Order" button will use controller to associate all the info from the previous scenes to update the Order Object.
 * Books that are bought will be updated as Sold accordingly.
 * 
 * @author Danny Hsu
 * @version 1.0
 */
public class Order3FinalScene extends NavigationBar implements Initializable
{
	@FXML
	private TableView<BookInformation> booksInOrderTV;

	@FXML
	private Label currentUserLabel;
	
	@FXML
	private Label currentCustomerLabel;
	
	@FXML
	private TableView<BookInformation> booksFinalOrderTV;
	
	@FXML
	private Label totalPriceLabel;
	
	@FXML
	private Button saveOrderButton;
	
	@FXML
	private Button cancelButton;
	
	@FXML
	private RadioButton inStoreOrderRadioButton;
	
	@FXML
	private RadioButton advanceOrderRadioButton;
	
	@FXML
	ToggleGroup group = new ToggleGroup();


	@FXML
	private VBox advanceOptionsBox;
	
	@FXML
	private ComboBox<String> orderStatusCB = new ComboBox<>(); 
	
	
	private static Controller controller = Controller.getInstance();

	// For Combo Box
	ObservableList<OrderStatus> orderStatusList = controller.getAllOrderStatus();
	ObservableList<String> orderStatusString = FXCollections.observableArrayList();
	
	// For Visual ONLY
	ObservableList<BookInformation> booksInOrderListInfo = controller.getCurrentBooksInfoList();

	// Book List
	ObservableList<Book> availableBookList = controller.getAllAvailableBookList();
	ObservableList<Book> soldBookList = controller.getAllSoldBookList();

	
	/**
	 * initialize method initialized the scene with nodes and methods
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		currentUserLabel.setText("Order created by: " + controller.getCurrentUser().getUsername());
		currentCustomerLabel.setText("Customer: " + controller.getCurrentCustomer().getName());
		totalPriceLabel.setText("Total Price: $" + controller.getCurrentFinalPrice());

		// Order Status ComboBox
		for (int i = 0; i < orderStatusList.size(); i++)
		{
			String orderString = orderStatusList.get(i).getOrderStatus();
			orderStatusString.add(orderString);
		}
		orderStatusCB.setItems(orderStatusString);
		
		// Books In Order View
		booksInOrderTV.setItems(booksInOrderListInfo);
		TableColumn<BookInformation, String> titleCol2 = new TableColumn<BookInformation, String>("Title");
		titleCol2.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("title"));
		TableColumn<BookInformation, String> authorCol2 = new TableColumn<BookInformation, String>("Author");
		authorCol2.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("author"));
		TableColumn<BookInformation, String> salesCol2 = new TableColumn<BookInformation, String>("Sales $");
		salesCol2.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("sales"));
		booksInOrderTV.getColumns().setAll(titleCol2, authorCol2, salesCol2);	
		
	}
	

	/**
	 * inStoreAction() method determine if the Advance Order - Order Status Option will become visible
	 * If the order is in-store --> hide the advance order options
	 */
	@FXML
	public void inStoreAction()
	{
		advanceOptionsBox.setVisible(false);	
	}
	/**
	 * advanceOrderAction() method determine if the Advance Order - Order Status Option will become visible
	 * If the order is Advance --> display the advance order options
	 */
	@FXML
	public void advanceOrderAction()
	{
		advanceOptionsBox.setVisible(true);	
	}
	/**
	 * finalSaveOrderAction() is the final step in the Create Order process
	 * All the info capture so far from the Scene (1) to (3) are used to UPDATE
	 * the New Empty Order we created in Step 1.
	 * 
	 * Controller is called to update all the related tables in the database
	 */
	@FXML
	public void finalSaveOrderAction()
	{

		double total = controller.getCurrentFinalPrice();
		int customerId = controller.getCurrentCustomer().getId();
		int userId = controller.getCurrentUser().getId();
		
		
		boolean orderType = false; // SELECTION FROM THE RADIO BUTTON
		
		int orderStatusId = 0;
		
		if (inStoreOrderRadioButton.isSelected())
		{
			orderType = false;
			orderStatusId = 0;
		}
		else
		{
			orderType = true;
			
			if (orderStatusCB.getSelectionModel().getSelectedItem() == orderStatusList.get(0).getOrderStatus())
			{
				orderStatusId = 1;
			}
			else if (orderStatusCB.getSelectionModel().getSelectedItem() == orderStatusList.get(1).getOrderStatus())
			{
				orderStatusId = 2;
			}
			else if (orderStatusCB.getSelectionModel().getSelectedItem() == orderStatusList.get(2).getOrderStatus())
			{
				orderStatusId = 3;
			}
			else if (orderStatusCB.getSelectionModel().getSelectedItem() == orderStatusList.get(3).getOrderStatus())
			{
				orderStatusId = 4;
			}
		}
		
		// Get Book ID and Order ID to Controller
		int orderId = controller.getCurrentOrderId();
		int bookId = 0;
		
		ArrayList<String> listOfIds = controller.getCurrentListOfBookIdsToOrder();
		for (String id : listOfIds)
		{
			// Add Order ID and Book ID to the relationship table
			bookId = Integer.parseInt(id);
			controller.joinBooksAndOrder(orderId, bookId);
			
			// Set Book Sold Attribute --> Sold
			controller.updateBookToSold(id);
			
			// Set Book object --> Sold AND Add to sold book list
			for (Book eachBook : availableBookList)
			{
				if (eachBook.getId() == Integer.parseInt(id))
				{
					eachBook.setSold(true);

					soldBookList.add(eachBook);
				}
			}
			// Remove sold book from available book list
			for (Book eachBook : soldBookList)
			{
				if (eachBook.getId() == Integer.parseInt(id))
				{
					availableBookList.remove(eachBook);		
				}
			}
		}
		String result = controller.createOrderControl(orderId, total, customerId, userId, orderType, orderStatusId);
		
		// Reset all controller variables except for currentUser
		controller.setCurrentCustomer(null);
		controller.setCurrentFinalPrice(0);
		controller.setCurrentListOfBookIdsToOrder(null);
		controller.setCurrentBooksInfoList(null);
		controller.setCurrentOrderId(0);
		
		if (result.equalsIgnoreCase("SUCCESS"))
	    {
			// go to Manage Orders
			this.returnOrders(); 
	    }
	    else
	    {
	    	// Home scene can delete inValid orders
	    	this.returnHome();
	    }
	}
}
