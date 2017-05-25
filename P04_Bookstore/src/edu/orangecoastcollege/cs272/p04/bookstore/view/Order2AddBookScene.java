package edu.orangecoastcollege.cs272.p04.bookstore.view;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;


import edu.orangecoastcollege.cs272.p04.bookstore.controller.Controller;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Author;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Book;
import edu.orangecoastcollege.cs272.p04.bookstore.model.BookInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * <code>Order2AddBookScene</code> is the 2nd scene of the Create Order process
 * User adds or remove books to the order, these are books bought by customer
 * User choose from list of available books from inventory to add or remove from order.
 * The list of books IDs are recorded, so we can add to the Order in the final scene.
 * @author Danny Hsu
 * @version 1.0
 */
public class Order2AddBookScene extends NavigationBar implements Initializable
{
	@FXML
	private TableView<BookInformation> bookTV;
	
	@FXML
	private TableView<BookInformation> booksInOrderTV;
		
	@FXML
	private Button addBookToOrderButton;

	@FXML
	private Button removeBookFromOrderButton;

	@FXML
	private Button continueButton;
	
	@FXML
	private Label totalPriceLabel;
	
	
	// Hold all the book ID to add to the Order_Book relationship table in the final scene
	ArrayList<String> myListOfBooksToOrder = new ArrayList<>();

	private static Controller controller = Controller.getInstance();

	ObservableList<Author> authorList = controller.getAllAuthors();
	ObservableList<Book> availableBookList = controller.getAllAvailableBookList();
	
	// For Visual ONLY
	ObservableList<BookInformation> mainList = FXCollections.observableArrayList();
	ObservableList<BookInformation> booksInOrderListInfo = FXCollections.observableArrayList();
	
	/**
	 * populateTable() method populate the TableView with ObservableList of Available Books
	 * so the user can add book to order
	 */
	public void populateTable()
	{
		int id = 0;
		String title = null;
		String author = null;
		double sales = 0;

		for (Book eachBook : availableBookList)
		{		
			id = eachBook.getId();
			title = eachBook.getTitle();
			sales = eachBook.getSales();

			for (Author eachAuthor : authorList)
			{
				if (eachBook.getAuthorId() == eachAuthor.getId())
					author = eachAuthor.getName();
				
				else if (eachBook.getAuthorId() == 0)
				{
					author = null;
				}
			}
			BookInformation mainBook = new BookInformation(id, title, author, sales);
			mainList.add(mainBook);
		}
	}
	
	/**
	 * continueButtonAbility() method determine if the continue button is usable
	 */
	public void continueButtonAbility()
	{
		// Enable the Continue Button only when there are list of books added to order
		if (booksInOrderListInfo.size() < 1)
			continueButton.setDisable(true);
		else
			continueButton.setDisable(false);		
	}
	
	
	/**
	 * initialize method initialized the scene with nodes and methods
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// Main View --> Populate with all the available books
		populateTable();
		bookTV.setItems(mainList);
		TableColumn<BookInformation, String> titleCol = new TableColumn<BookInformation, String>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("title"));
		TableColumn<BookInformation, String> authorCol = new TableColumn<BookInformation, String>("Author");
		authorCol.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("author"));
		TableColumn<BookInformation, String> salesCol = new TableColumn<BookInformation, String>("Sales $");
		salesCol.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("sales"));
		bookTV.getColumns().setAll(titleCol, authorCol, salesCol);

		// Books In Order View --> The User selected Books
		booksInOrderTV.setItems(booksInOrderListInfo);
		TableColumn<BookInformation, String> titleCol2 = new TableColumn<BookInformation, String>("Title");
		titleCol2.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("title"));
		TableColumn<BookInformation, String> authorCol2 = new TableColumn<BookInformation, String>("Author");
		authorCol2.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("author"));
		TableColumn<BookInformation, String> salesCol2 = new TableColumn<BookInformation, String>("Sales $");
		salesCol2.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("sales"));
		booksInOrderTV.getColumns().setAll(titleCol2, authorCol2, salesCol2);

		// selection actions
		bookTV.setOnMouseClicked(e-> selectedBookInMainView());
		booksInOrderTV.setOnMouseClicked(e-> selectedBookInOrderView());
		if (!bookTV.isFocused())
			addBookToOrderButton.setDisable(true);
		if (!booksInOrderTV.isFocused())
			removeBookFromOrderButton.setDisable(true);
		
		System.out.println("TEST The Current Cart has: " + booksInOrderListInfo.size());
		System.out.println("THIS IS CURRENT CUSTOMER " + controller.getCurrentCustomer());
		System.out.println("THIS IS CURRENT ORDER " + controller.getCurrentOrderId());


	}
	
	/**
	 * selectedBookInMainView() method capture the book selected in the Main View
	 * these are the books available to buy
	 * @return
	 */
	public BookInformation selectedBookInMainView()
	{
		BookInformation selected = bookTV.getSelectionModel().getSelectedItem();
		booksInOrderTV.getSelectionModel().clearSelection();
		removeBookFromOrderButton.setDisable(true);
		addBookToOrderButton.setDisable(false);
		return selected;
	}
	/**
	 * selectedBookInOrderView() method capture the book selected in the Order View
	 * these are books added to cart to be bought
	 * @return
	 */
	public BookInformation selectedBookInOrderView()
	{
		BookInformation selected = booksInOrderTV.getSelectionModel().getSelectedItem();
		bookTV.getSelectionModel().clearSelection();
		addBookToOrderButton.setDisable(true);
		removeBookFromOrderButton.setDisable(false);
		return selected;
	}
	
	/**
	 * addBookToOrderAction() method add book selected from MainView to OrderView
	 */
	@FXML
	public void addBookToOrderAction()
	{
		BookInformation selected = selectedBookInMainView();
		if ( selected != null)
		{
			booksInOrderListInfo.add(selected);
			mainList.remove(selected);
		}
		bookTV.getSelectionModel().clearSelection();
		calculateTotal();
		totalPriceLabel.setText("Total Price: $" + controller.getCurrentFinalPrice());

		System.out.println("TEST The Current Cart has: " + booksInOrderListInfo.size());

		
		continueButtonAbility();
	}
	
	/**
	 * removeBookFromOrderAction() method remove selected book from OrderView to MainView
	 */
	@FXML
	public void removeBookFromOrderAction()
	{
		BookInformation selected = selectedBookInOrderView();
		if ( selected!= null)
		{
			booksInOrderListInfo.remove(selected);
			mainList.add(selected);
		}
		booksInOrderTV.getSelectionModel().clearSelection();
		calculateTotal();
		totalPriceLabel.setText("Total Price: $" + controller.getCurrentFinalPrice());
		
		System.out.println("TEST The Current Cart has: " + booksInOrderListInfo.size());
		
		continueButtonAbility();

	}

	/**
	 * calculateTotal() method calculate the total price of the order based
	 * on the books added to the Order View
	 * Capture: total sales price
	 */
	public void calculateTotal()
	{
		double total = 0;

		for (BookInformation b : booksInOrderListInfo)
		{
			total += b.getSales();
		}
		DecimalFormat df = new DecimalFormat("#.00");
		controller.setCurrentFinalPrice(Double.parseDouble(df.format(total)));

	}
	
	/**
	 * continueAction() continue to the (3) Final Review scene in the Create Order process.
	 * Capture: list of books in order
	 */
	@FXML
	public void continueAction()
	{
		// Add all the books in order bookId to the list of ArrayList to carry to the next scene
		// this is the list of bookId that will be added to the BOOK_ORDER RELATIONSHIP table in the final step
		for (BookInformation b : booksInOrderListInfo)
		{
			myListOfBooksToOrder.add(String.valueOf(b.getId()));
		}
		controller.setCurrentListOfBookIdsToOrder(myListOfBooksToOrder);
		controller.setCurrentBooksInfoList(booksInOrderListInfo);
		
		System.out.println("TEST CURRENT LIST OF BOOK ID: " + controller.getCurrentListOfBookIdsToOrder() + "   FINAL PRICE -> " + controller.getCurrentFinalPrice());
		System.out.println("CURRENT USER: " + controller.getCurrentUser());
		
		returnOrder3Final();
	}
}
