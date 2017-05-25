package edu.orangecoastcollege.cs272.p04.bookstore.controller;

import edu.orangecoastcollege.cs272.p04.bookstore.model.DBModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import edu.orangecoastcollege.cs272.p04.bookstore.model.AccessLevel;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Author;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Book;
import edu.orangecoastcollege.cs272.p04.bookstore.model.BookInformation;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Condition;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Customer;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Genre;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Order;
import edu.orangecoastcollege.cs272.p04.bookstore.model.OrderInformation;
import edu.orangecoastcollege.cs272.p04.bookstore.model.OrderStatus;
import edu.orangecoastcollege.cs272.p04.bookstore.model.User;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * Controller, in the software architecture concept, controller is the go-between between GUI (View) and database (Model)
 * It accepts input and converts it to commands for the model or view
 * It can send commands to the model to update the model's state.
 * It can also send commands to its associated view to change the view's presentation of the model.
 * 
 * @author The Bookstore Team: Danny, Brian, Phil
 * @version 1.0
 */
public class Controller 
{
	private static Controller theOne;
	
	private static final String DB_NAME = "bookstore.db";

	private static final String USER_TABLE_NAME = "user";
	private static final String[] USER_FIELD_NAMES = { "id", "username", "password", "position", "accessLevel"};
	private static final String[] USER_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "INTEGER"};
	
	private static final String ACCESSLEVEL_TABLE_NAME = "access_level";
	private static final String[] ACCESSLEVEL_FIELD_NAMES = { "id", "accessLevel", "description"};
	private static final String[] ACCESSLEVEL_FIELD_TYPES = { "INTEGER PRIMARY KEY", "INTEGER", "TEXT"};

	private static final String ORDERSTATUS_TABLE_NAME = "order_status";
	private static final String[] ORDERSTATUS_FIELD_NAMES = { "id", "orderStatus"};
	private static final String[] ORDERSTATUS_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT"};

	private static final String AUTHOR_TABLE_NAME = "author";
	private static final String[] AUTHOR_FIELD_NAMES = { "id", "name", "born", "death"};
	private static final String[] AUTHOR_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT"};
	
	private static final String CONDITION_TABLE_NAME = "conditon";
	private static final String[] CONDITION_FIELD_NAMES = { "id", "condition"};
	private static final String[] CONDITION_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT"};

	private static final String GENRE_TABLE_NAME = "genre";
	private static final String[] GENRE_FIELD_NAMES = { "id", "genre"};
	private static final String[] GENRE_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT"};
	
	private static final String CUSTOMER_TABLE_NAME = "customer";
	private static final String[] CUSTOMER_FIELD_NAMES = { "id", "name", "address", "phone", "email"};
	private static final String[] CUSTOMER_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "TEXT"};

	private static final String BOOK_TABLE_NAME = "book";
	private static final String[] BOOK_FIELD_NAMES = { "id", "title", "pubDate", "edition", "salesPrice", "description", "sold", "genreId", "conditionId", "authorId"};
	private static final String[] BOOK_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "REAL", "TEXT", "INTEGER", "INTEGER", "INTEGER", "INTEGER"};
	

	private static final String ORDER_TABLE_NAME = "orderTable";
	private static final String[] ORDER_FIELD_NAMES = { "id", "total", "customerId", "userId", "orderType", "orderStatusId", "validOrder"};
	private static final String[] ORDER_FIELD_TYPES = { "INTEGER PRIMARY KEY", "REAL", "INTEGER", "INTEGER", "INTEGER", "INTEGER", "INTEGER"};
	
	// THE RELATIONSHIP TABLE -> To keep track of books added to the order
	private static final String BOOKS_IN_ORDER_TABLE_NAME = "books_in_order";
	private static final String[] BOOKS_IN_ORDER_FIELD_NAMES = { "orderId", "bookId" };
	private static final String[] BOOKS_IN_ORDER_FIELD_TYPES = { "INTEGER", "INTEGER" };
		
	private User mCurrentUser;
	private Customer mCurrentCustomer;
	private Author mCurrentAuthor;
	private Book mCurrentBook;
	private Order mCurrentOrder;
	private OrderInformation mCurrentOrderInfo;
	
	private ArrayList<String> mCurrentListOfBookIdsToOrder;
	private ObservableList<BookInformation> mCurrentBooksInfoList;
	
	private int mCurrentOrderId;
	private double mCurrentFinalPrice;
	
	private DBModel mAuthorDB;
	private DBModel mBookDB;
	private DBModel mConditionDB;
	private DBModel mCustomerDB;
	private DBModel mGenreDB;
	private DBModel mOrderDB;
	private DBModel mUserDB;
	private DBModel mOrderStatusDB;
	private DBModel mAccessLevelDB;
	private DBModel mBooksInOrderDB;


	private ObservableList<Author> mAllAuthorList;
	private ObservableList<Condition> mAllConditionList;
	private ObservableList<Customer> mAllCustomerList;
	private ObservableList<Genre> mAllGenreList;
	private ObservableList<Order> mAllOrderList;
	private ObservableList<User> mAllUserList;
	private ObservableList<OrderStatus> mAllOrderStatusList;
	private ObservableList<AccessLevel> mAllAccessLevelList;
	private ObservableList<Book> mAllBookList;

	
	// To keep track of current session's available books and sold books
	private ObservableList<Book> mAllAvailableBookList;
	private ObservableList<Book> mAllSoldBookList;
	
	private Controller() { }
	
	/**
	 * getInstance() method initiate the program, takes all the stuff from database to pull
	 * into the realm of Java objects - in this case, pull data into ObservableList
	 * @return theOne - our one and only controller
	 */
	public static Controller getInstance()
	{
		if (theOne == null)
		{
			theOne = new Controller();
			theOne.mAllAuthorList = FXCollections.observableArrayList();
			theOne.mAllConditionList = FXCollections.observableArrayList();
			theOne.mAllCustomerList = FXCollections.observableArrayList();
			theOne.mAllGenreList = FXCollections.observableArrayList();
			theOne.mAllOrderList = FXCollections.observableArrayList();
			theOne.mAllUserList = FXCollections.observableArrayList();
			theOne.mAllOrderStatusList = FXCollections.observableArrayList();
			theOne.mAllAccessLevelList = FXCollections.observableArrayList();
			
			theOne.mAllAvailableBookList = FXCollections.observableArrayList();
			theOne.mAllSoldBookList = FXCollections.observableArrayList();
			theOne.mAllBookList = FXCollections.observableArrayList();

			
			try {
				
				// User
				theOne.mUserDB = new DBModel(DB_NAME, USER_TABLE_NAME, USER_FIELD_NAMES, USER_FIELD_TYPES);
				
				// ***** HARDCODED TO CREATE ADMIN USER *****
				if (theOne.mUserDB.getRecordCount() == 0)
				{
					String[] fields = { "username", "password", "position", "accessLevel" };
					String[] values = { "admin", "12345678", "Owner", "1" };
					theOne.mUserDB.createRecord(fields, values);
				}
				
				// Create the resultsList variable, we will use it for rest of the list creation
				ArrayList<ArrayList<String>> resultsList = theOne.mUserDB.getAllRecords();
				
				for (ArrayList<String> eachItem : resultsList) 
				{
					int id = Integer.parseInt(eachItem.get(0));
					String username = eachItem.get(1);
					String password = eachItem.get(2);
					String position = eachItem.get(3);
					int accessLevel = Integer.parseInt(eachItem.get(4));
					theOne.mAllUserList.add(new User(id, username, password, position, accessLevel));
				}
				
				// Customer
				theOne.mCustomerDB = new DBModel(DB_NAME, CUSTOMER_TABLE_NAME, CUSTOMER_FIELD_NAMES, CUSTOMER_FIELD_TYPES);
				
				// ******** Initial Values ********
				if (theOne.mCustomerDB.getRecordCount() == 0)
				{
					String[] fields = { "name", "address", "phone", "email" };
					String[] values = { "Hello Kitty", "195 ABC Street", "949-555-5555", "hello@kitty.com" };
					theOne.mCustomerDB.createRecord(fields, values);
					
					String[] values2 = { "Yogi Bear", "592 ABC Street", "888-555-5555", "yogi@bear.com" };
					theOne.mCustomerDB.createRecord(fields, values2);
				}
				
				resultsList = theOne.mCustomerDB.getAllRecords();

				for (ArrayList<String> eachItem : resultsList)
				{						
					int id = Integer.parseInt(eachItem.get(0));
					String name = eachItem.get(1);
					String address = eachItem.get(2);
					String phone = eachItem.get(3);
					String email = eachItem.get(4);
					theOne.mAllCustomerList.add(new Customer(id, name, address, phone, email));
				}
				
				// Author
				theOne.mAuthorDB = new DBModel(DB_NAME, AUTHOR_TABLE_NAME, AUTHOR_FIELD_NAMES, AUTHOR_FIELD_TYPES);
				
				// ******** Initial Values ********
				if (theOne.mAuthorDB.getRecordCount() == 0)
				{
					String[] fields = { "name", "born", "death"};
					String[] values = { "Mark Twain", "November 30, 1835", "April 21, 1910" };
					theOne.mAuthorDB.createRecord(fields, values);
					
					String[] values2 = { "Jane Austen", "December 16, 1775", "July 18, 1817" };
					theOne.mAuthorDB.createRecord(fields, values2);
					
					String[] values3 = { "Ernest Hemingway", "July 21, 1899", "July 2, 1961" };
					theOne.mAuthorDB.createRecord(fields, values3);

				}
				
				resultsList = theOne.mAuthorDB.getAllRecords();

				for (ArrayList<String> eachItem : resultsList)
				{	
					int id = Integer.parseInt(eachItem.get(0));
					String name = eachItem.get(1);
					String born = eachItem.get(2);
					String death = eachItem.get(3);
					theOne.mAllAuthorList.add(new Author(id, name, born, death));
				}
				
				// Condition
				theOne.mConditionDB = new DBModel(DB_NAME, CONDITION_TABLE_NAME, CONDITION_FIELD_NAMES, CONDITION_FIELD_TYPES);
				
				// ******** Initial Values ********
				if (theOne.mConditionDB.getRecordCount() == 0)
				{
					String[] fields = { "condition"};
					String[] values = { "Good" };
					theOne.mConditionDB.createRecord(fields, values);
					String[] values2 = { "Excellent" };
					theOne.mConditionDB.createRecord(fields, values2);
					String[] values3 = { "Great" };
					theOne.mConditionDB.createRecord(fields, values3);
					String[] values4 = { "Dents on Hardcover" };
					theOne.mConditionDB.createRecord(fields, values4);
					String[] values5 = { "Water Damage" };
					theOne.mConditionDB.createRecord(fields, values5);
					
				}
				
				resultsList = theOne.mConditionDB.getAllRecords();
				
				for (ArrayList<String> eachItem : resultsList)
				{
					int id = Integer.parseInt(eachItem.get(0));
					String condition = eachItem.get(1);
					theOne.mAllConditionList.add(new Condition(id, condition));
				}
				
				// Genre
				theOne.mGenreDB = new DBModel(DB_NAME, GENRE_TABLE_NAME, GENRE_FIELD_NAMES, GENRE_FIELD_TYPES);
				
				// ******** Initial Values ********
				if (theOne.mGenreDB.getRecordCount() == 0)
				{
					String[] fields = { "genre"};
					String[] values = { "Mystery" };
					theOne.mGenreDB.createRecord(fields, values);
					String[] values2 = { "Science Fiction" };
					theOne.mGenreDB.createRecord(fields, values2);
					String[] values3 = { "Romance" };
					theOne.mGenreDB.createRecord(fields, values3);
					String[] values4 = { "Historical Fiction" };
					theOne.mGenreDB.createRecord(fields, values4);
					String[] values5 = { "Poetry" };
					theOne.mGenreDB.createRecord(fields, values5);
				}
				
				resultsList = theOne.mGenreDB.getAllRecords();

				for (ArrayList<String> eachItem : resultsList)
				{
					int id = Integer.parseInt(eachItem.get(0));
					String genre = eachItem.get(1);
					theOne.mAllGenreList.add(new Genre(id, genre));
				}

				// Access Level -- Use for ComboBox
				theOne.mAccessLevelDB = new DBModel(DB_NAME, ACCESSLEVEL_TABLE_NAME, ACCESSLEVEL_FIELD_NAMES, ACCESSLEVEL_FIELD_TYPES);
				
				// **** Hard Coded! ****
				// There are 4 levels of Access Level
				if (theOne.mAccessLevelDB.getRecordCount() == 0)
				{
					String[] fields = { "accessLevel", "description" };
					String[] values1 = { "1", "ADMIN" };
					theOne.mAccessLevelDB.createRecord(fields, values1);
					
					String[] values2 = { "2", "Assistant Manager" };
					theOne.mAccessLevelDB.createRecord(fields, values2);
					
					String[] values3 = { "3", "Salesperson" };
					theOne.mAccessLevelDB.createRecord(fields, values3);
					
					String[] values4 = { "4", "INACTIVE" };
					theOne.mAccessLevelDB.createRecord(fields, values4);
				}
				
				resultsList = theOne.mAccessLevelDB.getAllRecords();
				
				for (ArrayList<String> eachItem : resultsList)
				{			
					int id = Integer.parseInt(eachItem.get(0));
					int accessLevel = Integer.parseInt(eachItem.get(1));
					String description = eachItem.get(2);
					theOne.mAllAccessLevelList.add(new AccessLevel(id, accessLevel, description));
				}
				
				// Order Status -- Use for ComboBox
				theOne.mOrderStatusDB = new DBModel(DB_NAME, ORDERSTATUS_TABLE_NAME, ORDERSTATUS_FIELD_NAMES, ORDERSTATUS_FIELD_TYPES);
				
				// **** Hard Coded! ****
				// There are 4 levels of Order Status
				if (theOne.mOrderStatusDB.getRecordCount() == 0)
				{					
					String[] fields = { "orderStatus" };
					String[] values1 = { "To Be Picked Up" };
					theOne.mOrderStatusDB.createRecord(fields, values1);
					
					String[] values2 = { "To Be Shipped" };
					theOne.mOrderStatusDB.createRecord(fields, values2);
					
					String[] values3 = { "Picked Up By Customer" };
					theOne.mOrderStatusDB.createRecord(fields, values3);
					
					String[] values4 = { "Shipped" };
					theOne.mOrderStatusDB.createRecord(fields, values4);
				}
				
				resultsList = theOne.mOrderStatusDB.getAllRecords();
				
				for (ArrayList<String> eachItem : resultsList)
				{
					int id = Integer.parseInt(eachItem.get(0));
					String orderStatus = eachItem.get(1);
					theOne.mAllOrderStatusList.add(new OrderStatus(id, orderStatus));
				}
				
				// Book
				theOne.mBookDB = new DBModel(DB_NAME, BOOK_TABLE_NAME, BOOK_FIELD_NAMES, BOOK_FIELD_TYPES);
				
				// ******** Initial Values ********
				if (theOne.mBookDB.getRecordCount() == 0)
				{					
					//	"title", "pubDate", "edition", "salesPrice", "description", "sold", "genreId", "conditionId", "authorId"
					String[] fields = { "title", "pubDate", "edition", "salesPrice", "description", "sold", "genreId", "conditionId", "authorId" };
					String[] values1 = { "The Old Man and the Sea", "1952", "1st Edition-Charles Scribners Sons", "299.99", "In 1953, awarded the Pulitzer Prize for Fiction, and it was cited by the Nobel Committee as contributing to their awarding of the Nobel Prize in Literature to Hemingway in 1954", "0", "1", "2", "3" };
					theOne.mBookDB.createRecord(fields, values1);
					
					String[] values2 = { "For Whom the Bell Tolls", "21 October 1940", "2nd Ed", "199.99", "A Book of the Month Club choice, sold half a million copies within months, was nominated for a Pulitzer Prize", "0", "1", "1", "3" };
					theOne.mBookDB.createRecord(fields, values2);
					
					String[] values3 = { "A Moveable Feast", "December 1964", "2nd Edition", "59.99", "A memoir by American author Ernest Hemingway about his years as a struggling young expatriate journalist and writer in Paris in the 1920s.", "0", "1", "3", "3" };
					theOne.mBookDB.createRecord(fields, values3);
					
					String[] values4 = { "Islands in the Stream", "1970", "3rd Ed", "79.99", "Posthumously published", "0", "1", "4", "3" };
					theOne.mBookDB.createRecord(fields, values4);
					
					String[] values5 = { "The Garden of Eden", "1986", "1st Ed", "149.99", "Posthumously published", "0", "1", "1", "3" };
					theOne.mBookDB.createRecord(fields, values5);
					
					String[] values6 = { "The Snows of Kilimanjaro and Other Stories", "1961", "1st Ed", "289.99", "Collection of short stories", "0", "1", "1", "3" };
					theOne.mBookDB.createRecord(fields, values6);
					
					String[] values7 = { "Pride and Prejudice", "January 28, 1813", "1st Ed by Thomas Egerton", "599.99", "Pride and Prejudice is a novel by Jane Austen, first published in 1813", "0", "3", "3", "2" };
					theOne.mBookDB.createRecord(fields, values7);

					String[] values8 = { "Emma", "December 23, 1815", "1915 Reprint", "389.99", "The novel was first published in December 1815. Explores the concerns and difficulties of genteel women living in Georgian–Regency England", "0", "2", "3", "2" };
					theOne.mBookDB.createRecord(fields, values8);

					String[] values9 = { "Sense and Sensibility", "1811", "2nd Ed", "389.99", "The novel sold out its first print run of 750 copies in the middle of 1813, marking a success for its author, who then had a second print run later that year.", "0", "2", "3", "2" };
					theOne.mBookDB.createRecord(fields, values9);

					String[] values10 = { "Mansfield Park", "July 1814", "2nd Edn", "299.99", "The critical reception from the late 20th century onward has been controversial", "0", "2", "4", "2" };
					theOne.mBookDB.createRecord(fields, values10);

					String[] values11 = { "Persuasion", "1818", "1st Ed by John Murray London", "89.99", "Posthumously published", "0", "2", "1", "2" };
					theOne.mBookDB.createRecord(fields, values11);

					String[] values12 = { "Adventures of Huckleberry Finn", "December 10, 1884", "1st Ed", "759.99", "The book is noted for its colorful description of people and places along the Mississippi River.", "0", "3", "3", "1" };
					theOne.mBookDB.createRecord(fields, values12);

					String[] values13 = { "The Adventures of Tom Sawyer", "1876", "1st Ed by American Publishing Company", "869.99", "It is set in the fictional town of St. Petersburg, inspired by Hannibal, Missouri, where Twain lived", "0", "4", "1", "1" };
					theOne.mBookDB.createRecord(fields, values13);
				}
				
				resultsList = theOne.mBookDB.getAllRecords();
				
				for (ArrayList<String> eachItem : resultsList)
				{
					int id = Integer.parseInt(eachItem.get(0));
					String title = eachItem.get(1);
					String pubDate = eachItem.get(2);
					String edition = eachItem.get(3);
					double salesPrice = Double.parseDouble(eachItem.get(4));
					String description = eachItem.get(5);
					boolean sold = (eachItem.get(6).equals("0") ? false : true); // 0 is false, default
					int genreId = Integer.parseInt(eachItem.get(7));
					int conditionId = Integer.parseInt(eachItem.get(8));
					int authorId = Integer.parseInt(eachItem.get(9));

					// Complete List (not used)
					theOne.mAllBookList.add(new Book(id, title, pubDate, edition, salesPrice, description, sold, genreId, conditionId, authorId));

					// Sold List
					if (sold == true)
					{
						theOne.mAllSoldBookList.add(new Book(id, title, pubDate, edition, salesPrice, description, sold, genreId, conditionId, authorId));
					}
					
					// Available List
					if (sold == false)
					{
						theOne.mAllAvailableBookList.add(new Book(id, title, pubDate, edition, salesPrice, description, sold, genreId, conditionId, authorId));
					}
				}
				
				// Order -- There is No Initial Values to keep the Order ID correct in the DB
				theOne.mOrderDB = new DBModel(DB_NAME, ORDER_TABLE_NAME, ORDER_FIELD_NAMES, ORDER_FIELD_TYPES);
							
				resultsList = theOne.mOrderDB.getAllRecords();
				
				for (ArrayList<String> eachItem : resultsList)
				{
					int id = Integer.parseInt(eachItem.get(0));
					double total = Double.parseDouble(eachItem.get(1));
					int customerId = Integer.parseInt(eachItem.get(2));
					int userId = Integer.parseInt(eachItem.get(3));
					boolean orderType = (eachItem.get(4).equals("0") ? false : true); // 0 -> In-Store Order, 1 --> advance
					int orderStatusId = Integer.parseInt(eachItem.get(5));
					boolean validOrder = (eachItem.get(6).equals("0") ? false : true); // 0 is default --> false, order not finalized
					
					// Only need Valid Order in the Observable list
					if (validOrder == true)
					{
						theOne.mAllOrderList.add(new Order(id, total, customerId, userId, orderType, orderStatusId, validOrder));
					}
				}

				// RELATIONSHIP TABLE!
				theOne.mBooksInOrderDB = new DBModel(DB_NAME, BOOKS_IN_ORDER_TABLE_NAME, BOOKS_IN_ORDER_FIELD_NAMES, BOOKS_IN_ORDER_FIELD_TYPES);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return theOne;
	}
	
	
	// ********************************** ALL THE METHODS FROM THE SCENES!!!! **********************************

	// Add Author Scene --> Add Author Button
	/**
	 * addAuthorControl Adding a non-duplicate author to the database. Takes in three
	 * parameters such as the authors name, date of birth, and date of death. The
	 * parameters are put into an array. The whitespace is deleted temporarily while its
	 * name is being compared against other names. The Author is then added to the 
	 * database. The method returns the result whether its an error or a success.
	 * 
	 * @param name The name of an author e.g. "George R.R. Martin."
	 * @param dateOfBirth The date the author was born e.g. "December 6, 1942."
	 * @param dateOfDeath The date the author died e.g. "May 20, 1988."
	 * @return a string is returned with either a success, error, or already exists message.
	 */
	public String addAuthorControl(String name, String dateOfBirth, String dateOfDeath)
	{
		String[] values = { name, dateOfBirth, dateOfDeath };
		String authorNameNoWhiteSpace = name.replaceAll("^ +| +$|( )+", "");

		for (Author authorCheck : theOne.mAllAuthorList)
	        if(authorCheck.getName().replaceAll("^ +| +$|( )+", "").equalsIgnoreCase(authorNameNoWhiteSpace))
	            return authorCheck.getName() + " already exists.";

		try
		{
			int id = theOne.mAuthorDB.createRecord(Arrays.copyOfRange(AUTHOR_FIELD_NAMES, 1, AUTHOR_FIELD_NAMES.length), values);

			theOne.mAllAuthorList.add(new Author(id, name, dateOfBirth, dateOfDeath));
		}
		catch (SQLException e)
		{
			return "error";
		}
		return "SUCCESS";
	}
	
	
	
	
	// Add Genre Scene --> Save Genre Button
	/**
	 * addGenreControl Adds a non-duplicate genre to the database. Takes in one
	 * parameter such as the genre description. The parameter is put into an array. 
	 * The genre's description is checked for whitespace. The whitespace is deleted 
	 * temporarily while its name is being compared against other names. The condition is
	 * then added to the database. The method returns the result whether its an error or 
	 * a success.
	 * 
	 * @param genre A books genre e.g. "Science Fiction."
	 * @return a string is returned with either a success, error, or already exists message.
	 */
	public String addGenreControl(String genre)
	{
		String[] values = {genre};
		String genreNoWhiteSpace = genre.replaceAll("^ +| +$|( )+", "");

		for (Genre genreCheck : theOne.mAllGenreList)
	        if(genreCheck.getGenre().replaceAll("^ +| +$|( )+", "").equalsIgnoreCase(genreNoWhiteSpace))
	            return genreCheck.getGenre() + " genre already exists.";

		try
		{
			int id = theOne.mGenreDB.createRecord(Arrays.copyOfRange(GENRE_FIELD_NAMES, 1, GENRE_FIELD_NAMES.length), values);

			theOne.mAllGenreList.add(new Genre(id, genre));
		}
		catch (SQLException e)
		{
			return "error";
		}

		return "SUCCESS";
	}
	
	// Add Customer Scene --> Save Customer Button
	/**
	 * addCustomerControl Adding a non-duplicate customer to the database. Takes in four
	 * parameters such as the customer's name, address, phone, and email address. The
	 * parameters are put into an array. The whitespace is deleted temporarily while its
	 * name is being compared against other names. The customer is then added to the 
	 * database. The method returns the result whether its an error or a success.
	 *
	 * @param name The name of the customer e.g. "Dan."
	 * @param address The address of the customer e.g. "542 MacArthur, Newport Beach, CA, 92625."
	 * @param phone The phone number of the customer e.g. "949 887 2281"
	 * @param email The email address of the customer e.g. "andy@hotmail.com"
	 * @return a string is returned with either a success, error, or already exists message.
	 */
	public String addCustomerControl(String name, String address, String phone, String email)
	{
		String[] values = { name, address, phone, email};

        String nameNoWhiteSpace = name.replaceAll("^ +| +$|( )+", "");

        for (Customer customerCheck : theOne.mAllCustomerList)
            if(customerCheck.getName().replaceAll("^ +| +$|( )+", "").equalsIgnoreCase(nameNoWhiteSpace))
                return customerCheck.getName() + " already exists.";

		try
		{
			int id = theOne.mCustomerDB.createRecord(Arrays.copyOfRange(CUSTOMER_FIELD_NAMES, 1, CUSTOMER_FIELD_NAMES.length), values);
			theOne.mAllCustomerList.add(new Customer(id, name, address, phone, email));
		}
		catch (SQLException e)
		{
			return "Error";
		}

		return "Success";
	}
	
	// Add Condition Scene --> Add Condition Button
	/**
	 * addConditionControl Adds a non-duplicate condition to the database. Takes in one
	 * parameter such as the conditions description. The parameter is put into an array. 
	 * The condition's description is checked for whitespace. The whitespace is deleted 
	 * temporarily while its name is being compared against other names. The condition is
	 * then added to the database. The method returns the result whether its an error or 
	 * a success.
	 * 
	 * @param condition A books condition e.g. "New."
	 * @return a string is returned with either a success, error, or already exists message.
	 */
	public String addConditionControl(String condition)
	{
		String[] values = {condition};
		String conditionNoWhiteSpace = condition.replaceAll("^ +| +$|( )+", "");

		for (Condition conditionCheck : theOne.mAllConditionList)
	        if(conditionCheck.getCondition().replaceAll("^ +| +$|( )+", "").equalsIgnoreCase(conditionNoWhiteSpace))
	            return conditionCheck.getCondition() + " condition already exists.";

		try
		{
			int id = theOne.mConditionDB.createRecord(Arrays.copyOfRange(CONDITION_FIELD_NAMES, 1, CONDITION_FIELD_NAMES.length), values);

			theOne.mAllConditionList.add(new Condition(id, condition));
		}
		catch (SQLException e)
		{
			return "error";
		}
		return "SUCCESS";
	}

	// Add User Scene --> Add User Button
		/**
		 * it controls the add button for users.
		 * This takes in parameters in order for
		 * the User class to be updated and the
		 * new user to be inserted into the db.
		 * @param username
		 * @param password
		 * @param position
		 * @param accessLevel
		 * @return
		 */
	public String addUserControl(String username, String password, String position, int accessLevel){
		for(User user: theOne.mAllUserList)
			if(user.getUsername().equalsIgnoreCase(username))
				return "same username error";



		String[] values = {username, password, position, String.valueOf(accessLevel)};

		try {
			int id = theOne.mUserDB.createRecord(Arrays.copyOfRange(USER_FIELD_NAMES, 1, USER_FIELD_NAMES.length), values);
			theOne.mAllUserList.add(new User(id, username, password, position, accessLevel));
		} catch (SQLException e) {
			return "Error";
		}
		return "Success";
	}

	// Login Scene --> Login Button
	/**
	 * Logs in the user with the required username and
	 * password given in by the parameters.  It checks 
	 * thru the database, and return a negative if the 
	 * username and pasword fails
	 * @param username
	 * @param password
	 * @return
	 */	public String logInUser(String username, String password) {
		for(User user: theOne.mAllUserList){
			if(user.getUsername().equalsIgnoreCase(username)){
				//query the database for password
				try {
					ArrayList<ArrayList<String>> resultsList = theOne.mUserDB.getRecord(String.valueOf(user.getId()));
					String storedPassword = resultsList.get(0).get(2);
					if(password.equals(storedPassword)){
						theOne.mCurrentUser = user;
						if(user.getAccessLevel() != 4)
							return "Success";
						else
							return "Invalid User";
					}
					else
						return "username and/or password does not match";

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return "username and/or password does not match";
	}

	// Add Book Scene --> Add Book Button
	 /**
	  * addBookControl Method gets all the info from Add Book Scene to add
	  * to the database AND the ObservableList
	  * @param title - the title of the book
	  * @param pubDate - the pub date of the book
	  * @param edition - the edition of the book
	  * @param salesPrice - the sales price of the book
	  * @param description - the description of the book
	  * @param sold - the sold or not sold bit --> All Books Added are NOT sold
	  * @param genreId - the Genre ID --> link to Genre table
	  * @param conditionId --> the Condition ID --> link to Condition table
	  * @param authorId --> the Author ID --> link to Author table
	  * @return
	  */
	public String addBookControl(String title, String pubDate, String edition, double salesPrice, 
			String description,	boolean sold, int genreId, int conditionId, int authorId) 
	{
		// Sold value is 0 for false because a newly added book is NOT sold
		String[] values = { title, pubDate, edition, String.valueOf(salesPrice), description, "0" , String.valueOf(genreId), String.valueOf(conditionId), String.valueOf(authorId) };
		
		try 
		{
			int id = theOne.mBookDB.createRecord(Arrays.copyOfRange(BOOK_FIELD_NAMES, 1, BOOK_FIELD_NAMES.length), values);
			theOne.mAllAvailableBookList.add(new Book(id, title, pubDate, edition, salesPrice, description, sold, genreId, conditionId, authorId));
			theOne.mAllBookList.add(new Book(id, title, pubDate, edition, salesPrice, description, sold, genreId, conditionId, authorId));
		} 
		catch (SQLException e) 
		{
			return "Error";
		}
		return "Success";

	}
	
	// Home Scene --> Automatically delete INVALID ORDER
	/**
	 * 	deleteInvalidOrder method --> Delete InValid Order from Order Table
	 *  Order --> If an Order has the validity attribute of "false", then delete it from the table
	 *  Note: this is when the complete order process was NOT finished - e.g. when user closed the window before hitting the "final" Save Order button
	 *  This order was never added to the ObservableList, it purely exist in the database
	 *  This order created because we need the orderId to make the relationship table with bookId --> books_in_order table
	 */
	public void deleteInvalidOrder()
	{
		try 
		{
			ArrayList<ArrayList<String>> resultsList = theOne.mOrderDB.getAllRecords();
			for (ArrayList<String> eachOrder : resultsList)
			{
				String validOrder = eachOrder.get(6);
				if (validOrder.equals("0")) // 0 means NOT valid order
				{
					System.out.println("TEST This will delete this order with ID: " + eachOrder.get(0));
					String id = eachOrder.get(0); // get the id of this invalid order
					theOne.mOrderDB.deleteRecord(id); // delete invalid order!!!!
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	// Order (1) Add Customer Scene --> Continue Button --> Create Empty Order
	/**
	 * createNewEmptyOrderControl Method is initiated on the Continue Button in the
	 * Order (1) Add Customer Scene. An Empty order is created, this way we will have
	 * the Order ID created in the Order Table for use later when we finalized the order.
	 */
	public void createNewEmptyOrderControl() 
	{
		// Create a new Order so we can retrieve its id later in the final scene
		//"total", "customerId", "userId", "orderType", "orderStatusId", "validOrder"
		String[] values = { "0", "0", "0", "0", "0", "0" };

		try 
		{
			int id = theOne.mOrderDB.createRecord(Arrays.copyOfRange(ORDER_FIELD_NAMES, 1, ORDER_FIELD_NAMES.length), values);
			mCurrentOrderId = id;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	// Order (3) Review Scene --> Save Order Button --> books_in_order relationship table
	/**
	 * joinBooksAndOrder method will create new row in the Book_In_Order relationship table
	 * by joining the Order ID and the Book ID so we know which books are sold in which order
	 * 
	 * @param orderId - Order ID
	 * @param bookId -  Book ID
	 */
	public void joinBooksAndOrder(int orderId, int bookId)
	{
		String[] values = { String.valueOf(orderId), String.valueOf(bookId) };

		try 
		{
			theOne.mBooksInOrderDB.createRecord(BOOKS_IN_ORDER_FIELD_NAMES, values);
			// TEST
			System.out.println(theOne.mBooksInOrderDB.getAllRecords().toString());
			

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
	// Order (3) Review Scene --> Save Order Button --> Set the Book as "Sold"
	/**
	 * updateBookToSold method will update a Sold Book as Sold by updating the 
	 * Sold boolean bit in the table
	 * @param id - Book ID
	 */
	public void updateBookToSold(String id) 
	{
		String[] fields = { BOOK_FIELD_NAMES[6]}; // this is the Sold bit
		String[] values = { "1" };
		try 
		{
			theOne.mBookDB.updateRecord(id, fields, values);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	// Order (3) Review Scene --> Save Order Button --> Final Save Order --> UPDATE on the Empty Order we created earlier
	/**
	 * createOrderControl Method is the Final Action in the Create Order Process
	 * It will gather all the information about an Order and Update the Empty Order
	 * we created in the Order (1) Add Customer Scene
	 * @param orderId - the Order ID --> This is the Order that is created in the process
	 * @param total - the Order total price
	 * @param customerId - the Customer ID --> Link to Customer Table
	 * @param userId - the User ID --> Link to User Table
	 * @param orderType - the Order Type --> In-Store or Advance Order
	 * @param orderStatusId - the Order Status ID --> Link to Order Status Table
	 * @return
	 */
	public String createOrderControl(int orderId, double total, int customerId, int userId, boolean orderType, int orderStatusId) 
	{
		String orderTypeString = null; // 0-> is in store, 1-> is advance
		if (orderType == false)
			orderTypeString = "0";
		else
			orderTypeString = "1";
			
		String[] fields = {"total", "customerId", "userId", "orderType", "orderStatusId", "validOrder"};
		String[] values = { String.valueOf(total), String.valueOf(customerId), String.valueOf(userId),
				orderTypeString, String.valueOf(orderStatusId), "1"}; // the validOrder bit --> 1 as true
		
		try 
		{
			theOne.mOrderDB.updateRecord(String.valueOf(orderId), fields, values);
			theOne.mAllOrderList.add(new Order(orderId, total, customerId, userId, orderType, orderStatusId, true));

		} catch (SQLException e) 
		{
			
			e.printStackTrace();
			return "error";
		}
		
		return "Success";

	}
	
	
	// Manage Order Scene --> To Display Book Titles in Order
	/**
	 * getBookTitlesInOrder method is used to display the Books in Order
	 * it will use the Order ID to go get the data from the relationship table Books_In_Order
	 * to retrieved books that are part of this order
	 * @param orderId - the Order ID
	 * @return An Array List of String that are the Book Titles and Sales Price of the books
	 */
	public ArrayList<String> getBookTitlesInOrder(int orderId) 
	{
		ArrayList<String> bookTitleList = new ArrayList<>();
		try 
		{
			// Loop Books In Order Table where the Order ID was supplied in @param
			ArrayList<ArrayList<String>> resultsList = theOne.mBooksInOrderDB.getRecord(String.valueOf(orderId));
			for (int i = 0; i < resultsList.size(); i++)
			{
				String bookId = resultsList.get(i).get(1); // book ID field in the books_in_order table
				
				// Loop Books Table where ID was retrieved from Books In Order Table
				ArrayList<ArrayList<String>> bookList = theOne.mBookDB.getRecord(String.valueOf(bookId));
				for (int j = 0; j < bookList.size(); j++)
				{
					String bookTitle = bookList.get(j).get(1) + " - $" + bookList.get(j).get(4);
					bookTitleList.add(bookTitle);
				}
			}

		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookTitleList;
	}

	
	//********************* TABLE VIEW UPDATE CELLS *********************
	//*** These Codes will update the TableView Cells when double-clicked on them ***
	//*** They correspond to the codes in Scenes that have Column Object configured to be editable ***
	
	// Manage Customer Scenes --> Edit Cell --> Update Customer Name
	/**
	 * takes the old customer name and updates
	 * it to a new name for that specific cell
	 * and in the database and its according list
	 * as well
	 * @param id
	 * @param oldValue
	 * @param newValue
	 */
	public void updateCustomerNameControl(int id, String oldValue, String newValue) {
		String[] fields = {CUSTOMER_FIELD_NAMES[1]};
		String[] values = {newValue};
		
		try {
			theOne.mCustomerDB.updateRecord(String.valueOf(id), fields, values);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	// Manage Customer Scenes --> Edit Cell --> Update Customer Address
	/**
	 * takes the old customer address and updates it to
	 * a new customer address in that specific cell
	 * and in the database and its according list
	 * as well
	 * @param id
	 * @param oldValue
	 * @param newValue
	 */
	public void updateCustomerAddressControl(int id, String oldValue, String newValue) {
		String[] fields = {CUSTOMER_FIELD_NAMES[2]};
		String[] values = {newValue};
		
		try {
			theOne.mCustomerDB.updateRecord(String.valueOf(id), fields, values);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	// Manage Customer Scenes --> Edit Cell --> Update Customer Phone
	/**
	 * takes the customer's phone number and updates it to
	 * a new customer phone number in that specific cell
	 * and in the database and its according list
	 * as well
	 * @param id
	 * @param oldValue
	 * @param newValue
	 */
	public void updateCustomerPhoneControl(int id, String oldValue, String newValue) {
		String[] fields = {CUSTOMER_FIELD_NAMES[3]};
		String[] values = {newValue};
		
		try {
			theOne.mCustomerDB.updateRecord(String.valueOf(id), fields, values);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	// Manage Customer Scenes --> Edit Cell --> Update Customer Email
	/**
	 * takes the old customer email and updates it 
	 * to the new customer email in that specific cell
	 * and in the database and its according list
	 * as well
	 * @param id
	 * @param oldValue
	 * @param newValue
	 */
	public void updateCustomerEmailControl(int id, String oldValue, String newValue) {
		String[] fields = {CUSTOMER_FIELD_NAMES[4]};
		String[] values = {newValue};
		
		try {
			theOne.mCustomerDB.updateRecord(String.valueOf(id), fields, values);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	// Manage Users Scenes --> Edit Cell --> Update Username
	/**
	 * takes the id, old username and new adjusted name
	 * and replaces the old username to the new username
	 * in that specific cell
	 * and in the database and its according list
	 * as well
	 * @param id
	 * @param oldUsername
	 * @param newUsername
	 * @return
	 */
	public Object updateUsernameControl(int id, String oldUsername, String newUsername){
		String[] fields = {USER_FIELD_NAMES[1]};
		String[] values = {newUsername};
				try {
					theOne.mUserDB.updateRecord(String.valueOf(id), fields, values);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		return this;
	}
	// Manage Users Scenes --> Edit Cell --> Update Password
	/**
	 * takes the old password and updates it to the new
	 * password for that specific cell
	 * and in the database and its according list
	 * as well
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public Object updatePasswordControl(int id, String oldPassword, String newPassword){
		String[] fields = {USER_FIELD_NAMES[2]};
		String[] values = {newPassword};
				try {
					theOne.mUserDB.updateRecord(String.valueOf(id), fields, values);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		return this;
	}
	// Manage Users Scenes --> Edit Cell --> Update Position
	/**
	 * takes the old position and updates it to a 
	 * new position label in that specific cell
	 * and in the database and its according list
	 * as well
	 * @param id
	 * @param oldPosition
	 * @param newPosition
	 * @return
	 */
	public Object updatePositionControl(int id, String oldPosition, String newPosition){
		String[] fields = {USER_FIELD_NAMES[3]};
		String[] values = {newPosition};
				try {
					theOne.mUserDB.updateRecord(String.valueOf(id), fields, values);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		return this;
	}
	// Manage Users Scenes --> Edit Cell --> Update Access Level
	/**
	 * takes the old access level and updates it to
	 * the new access level in that specific cell
	 * and in the database and its according list
	 * as well
	 * @param id
	 * @param oldAccess
	 * @param newAccess
	 * @return
	 */
	public String updateAccessLevelControl(int id, Integer oldAccess, Integer newAccess){
 
		if (oldAccess == 1)
			return "Access Level 1 CANNOT be altered!"
					+ "\nYour invalid value was not set.";
		
		if (newAccess == 1)
			return "There can only be one Access Level 1 user"
					+ "\n Access Level can ONLY be the following:\n 2 for Assistant Manager\n 3 for Salesperson"
					+ "\n 4 for Inactive User \nYour invalid value was not set.";
		if (newAccess >= 2 && newAccess <= 4)
		{
			String[] fields = {USER_FIELD_NAMES[4]};
			String[] values = { newAccess.toString() };
 
					try {
						theOne.mUserDB.updateRecord(String.valueOf(id), fields, values);
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return "SUCCESS";
		}
		return "Access Level can ONLY be the following:\n 2 for Assistant Manager \n 3 for Salesperson"
				+ "\n 4 for Inactive User \nYour invalid value was not set.";
	}
	
	
	// Manage Orders Scenes --> Edit Cell --> Update Order Status
	/**
	 * Takes the old order status and updates it to
	 * the new order status in that specific cell
	 * and in the database and its according list
	 * as well
	 * @param orderId
	 * @param oldValue
	 * @param newValue
	 */
	public void updateOrderStatusControl(int orderId, String oldValue, String newValue) 
	{
		String orderStatusId = null;
		if (newValue == "To Be Picked Up")
		{
			orderStatusId = "1";
		}
		if (newValue == "To Be Shipped")
		{
			orderStatusId = "2";
 
		}
		if (newValue == "Picked Up")
		{
			orderStatusId = "3";
 
		}
		if (newValue == "Shipped")
		{
			orderStatusId = "4";
 
		}
		if (newValue == " - ")
		{
			orderStatusId = "0";
		}
		String[] fields = { ORDER_FIELD_NAMES[5]};
		String[] values = { orderStatusId };
		
		try {
			theOne.mOrderDB.updateRecord(String.valueOf(orderId), fields, values);
			// Must update the Order object as well, in order for the All Order List to be updated
			// and display the most current update in the scene without re-launching the application
			// because in Manage Order scene, all the Table View Observable List depend on the All Order List
			for (Order o : theOne.mAllOrderList)
			{
				if (o.getId() == orderId)
				{
					o.setOrderStatusId(Integer.parseInt(orderStatusId));
				}
			}
 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
//*******************************************************************************************
/**
 * All the Controller Objects Methods:
 * 
 * ObservableList:
 * These are the list of Java Objects created from the Database
 * 
 * Gets and Sets:
 * Getter and Setter of the Controller Object, for when we want to hold Object information
 * from scene to scene (e.g. During the Create Order process, we need to keep track of 
 * User, Customer, Books, Order through different scenes)
 * 
 * Danny's note: so I created most of them just in case I need to use them
 * for the application, but many of them ended up unused. But I've lost track of them
 * so I ended up not deleting the unused ones.
 * 
 */
	public ObservableList<Author> getAllAuthors() 
	{
		return theOne.mAllAuthorList;
	}
	public ObservableList<Book> getAllBookList() {
		return mAllBookList;
	}
	public void setAllBookList(ObservableList<Book> allBookList) {
		mAllBookList = allBookList;
	}
	public ObservableList<Condition> getAllConditions() 
	{
		return theOne.mAllConditionList;
	}
	public ObservableList<Customer> getAllCustomers() 
	{
		return theOne.mAllCustomerList;
	}
	public ObservableList<Genre> getAllGenre() 
	{
		return theOne.mAllGenreList;
	}
	public ObservableList<Order> getAllOrders() 
	{
		return theOne.mAllOrderList;
	}
	public ObservableList<OrderStatus> getAllOrderStatus() 
	{
		return theOne.mAllOrderStatusList;
	}	
	public ObservableList<User> getAllUsers() 
	{
		return theOne.mAllUserList;
	}
	public ObservableList<AccessLevel> getAllAccessLevels() 
	{
		return theOne.mAllAccessLevelList;
	}
	public User getCurrentUser() {
		return mCurrentUser;
	}
	public void setCurrentUser(User currentUser) {
		mCurrentUser = currentUser;
	}
	public Customer getCurrentCustomer() {
		return mCurrentCustomer;
	}
	public void setCurrentCustomer(Customer currentCustomer) {
		mCurrentCustomer = currentCustomer;
	}
	public Author getCurrentAuthor() {
		return mCurrentAuthor;
	}
	public void setCurrentAuthor(Author currentAuthor) {
		mCurrentAuthor = currentAuthor;
	}
	public Book getCurrentBook() {
		return mCurrentBook;
	}
	public void setCurrentBook(Book currentBook) {
		mCurrentBook = currentBook;
	}
	public Order getCurrentOrder() {
		return mCurrentOrder;
	}
	public void setCurrentOrder(Order currentOrder) {
		mCurrentOrder = currentOrder;
	}
	public ArrayList<String> getCurrentListOfBookIdsToOrder() {
		return mCurrentListOfBookIdsToOrder;
	}
	public void setCurrentListOfBookIdsToOrder(ArrayList<String> currentListOfBookIdsToOrder) {
		mCurrentListOfBookIdsToOrder = currentListOfBookIdsToOrder;
	}
	public double getCurrentFinalPrice() {
		return mCurrentFinalPrice;
	}
	public void setCurrentFinalPrice(double currentFinalPrice) {
		mCurrentFinalPrice = currentFinalPrice;
	}
	public ObservableList<BookInformation> getCurrentBooksInfoList() {
		return mCurrentBooksInfoList;
	}
	public void setCurrentBooksInfoList(ObservableList<BookInformation> currentBooksInfoList) {
		mCurrentBooksInfoList = currentBooksInfoList;
	}
	public int getCurrentOrderId() {
		return mCurrentOrderId;
	}
	public void setCurrentOrderId(int currentOrderId) {
		mCurrentOrderId = currentOrderId;
	}
	public ObservableList<Book> getAllSoldBookList() {
		return mAllSoldBookList;
	}
	public void setAllSoldBookList(ObservableList<Book> allSoldBookList) {
		mAllSoldBookList = allSoldBookList;
	}
	public ObservableList<Book> getAllAvailableBookList() {
		return mAllAvailableBookList;
	}
	public void setAllAvailableBookList(ObservableList<Book> allAvailableBookList) {
		mAllAvailableBookList = allAvailableBookList;
	}
	public OrderInformation getCurrentOrderInfo() {
		return mCurrentOrderInfo;
	}
	public void setCurrentOrderInfo(OrderInformation currentOrderInfo) {
		mCurrentOrderInfo = currentOrderInfo;
	}

	
}