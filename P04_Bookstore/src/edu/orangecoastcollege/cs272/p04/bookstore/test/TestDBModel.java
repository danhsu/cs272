package edu.orangecoastcollege.cs272.p04.bookstore.test;
 
import static org.junit.Assert.*;
 
import java.sql.SQLException;
 
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
 
import edu.orangecoastcollege.cs272.p04.bookstore.model.DBModel;
/**
 * This is for Junit Testing
 * @author bookstore team
 * @version 1.0
 */
public class TestDBModel {
	
	private static final String DB_NAME = "bookstore_test.db";
	 
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
	
	private static DBModel dbUsers;
	private static DBModel dbAccessLevel;
	private static DBModel dbOrderStatus;
	private static DBModel dbAuthor;
	private static DBModel dbCondition;
	private static DBModel dbGenre;
	private static DBModel dbCustomer;
	private static DBModel dbBook;
	private static DBModel dbOrder;
	
	private String[] userValues;
	private String[] accessLevelValues;
	private String[] orderStatusValues;
	private String[] authorValues;
	private String[] conditionValues;
	private String[] genreValues;
	private String[] customerValues;
	private String[] bookValues;
	private String[] orderValues;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbUsers = new DBModel(DB_NAME, USER_TABLE_NAME, USER_FIELD_NAMES, USER_FIELD_TYPES);
		dbAccessLevel = new DBModel(DB_NAME, ACCESSLEVEL_TABLE_NAME, ACCESSLEVEL_FIELD_NAMES, ACCESSLEVEL_FIELD_TYPES);
		dbOrderStatus = new DBModel(DB_NAME, ORDERSTATUS_TABLE_NAME, ORDERSTATUS_FIELD_NAMES, ORDERSTATUS_FIELD_TYPES);
		dbAuthor = new DBModel(DB_NAME, AUTHOR_TABLE_NAME, AUTHOR_FIELD_NAMES, AUTHOR_FIELD_TYPES);
		dbCondition = new DBModel(DB_NAME, CONDITION_TABLE_NAME, CONDITION_FIELD_NAMES, CONDITION_FIELD_TYPES);
		dbGenre = new DBModel(DB_NAME, GENRE_TABLE_NAME, GENRE_FIELD_NAMES, GENRE_FIELD_TYPES);
		dbCustomer = new DBModel(DB_NAME, CUSTOMER_TABLE_NAME, CUSTOMER_FIELD_NAMES, CUSTOMER_FIELD_TYPES);
		dbBook = new DBModel(DB_NAME, BOOK_TABLE_NAME, BOOK_FIELD_NAMES, BOOK_FIELD_TYPES);
		dbOrder = new DBModel(DB_NAME, ORDER_TABLE_NAME, ORDER_FIELD_NAMES, ORDER_FIELD_TYPES);
	}
 
	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
		// Our new DBModel design took care of closing connections
	}
 
	@Before
	public void setUp() throws Exception {
		userValues = new String[]{"1","Guy","Password","Manager","1",};
		accessLevelValues = new String[]{"1","1","Admin"};
		orderStatusValues = new String[]{"1","To Be Shipped"};
		authorValues = new String[]{"1","Dead Author","April 2, 2015","June 3, 2017"};
		conditionValues = new String[]{"1","Good"};
		genreValues = new String[]{"1","Fantasy"};
		customerValues = new String[]{"1","Surf Dude","123 Waves Lane Cool Beach CA 92222","(777)777-7771","surfdude01@gmail.com"};
		bookValues = new String[]{"1","So much to learn","May 2, 2005","1st","99.99","New Book Bout Stuff","1","1","1","1"};
		orderValues = new String[]{"1","1","1","1","1","1","1"};
	}
 
	@After
	public void tearDown() throws Exception {
		dbUsers.deleteAllRecords();
		dbAccessLevel.deleteAllRecords();
		dbOrderStatus.deleteAllRecords();
		dbAuthor.deleteAllRecords();
		dbCondition.deleteAllRecords();
		dbGenre.deleteAllRecords();
		dbCustomer.deleteAllRecords();
		dbBook.deleteAllRecords();
		dbOrder.deleteAllRecords();
	}
 
	
	@Test
	public void testGetAllRecords() {
		try 
		{
			dbUsers.getAllRecords();
			dbAccessLevel.getAllRecords();
			dbAuthor.getAllRecords();
			dbCondition.getAllRecords();
			dbGenre.getAllRecords();
			dbCustomer.getAllRecords();
			dbBook.getAllRecords();
			dbOrder.getAllRecords();
		} 
		catch (SQLException e) 
		{
			fail("Getting all records on empty database should not generate SQLException");
		}
				
	}
 
	@Test
	public void testGetRecord() 
	{
		String[] accessLevelValue2 = { "2", "Assistant Manager" };

		try {
			dbOrder.createRecord(ORDER_FIELD_NAMES, orderValues);
			dbOrder.getRecord("1");
			assertEquals("Expect record to be 1", 1, dbOrder.getRecordCount());

			dbAccessLevel.createRecord(ACCESSLEVEL_FIELD_NAMES, accessLevelValues);
			dbAccessLevel.createRecord(ACCESSLEVEL_FIELD_NAMES, accessLevelValue2);
			dbAccessLevel.getRecord("2");
			assertEquals("Expect record to be 1", 1, dbAccessLevel.getRecordCount());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
 
	@Test
	public void testGetRecordCount() 
	{
		String[] customerValues2 = new String[]{"2","Surf Dude2","579 Waves Lane","(777) 999-3838",""};
		String[] orderValues2 = new String[]{"2","0","0","0","0","0","0"};

		try {
			
			dbCustomer.createRecord(CUSTOMER_FIELD_NAMES, customerValues);
			dbCustomer.createRecord(CUSTOMER_FIELD_NAMES, customerValues2);
			assertEquals("Expect record to be 2", 2, dbCustomer.getRecordCount());
			
			dbBook.createRecord(BOOK_FIELD_NAMES, bookValues);
			assertEquals("Expect record to be 1", 1, dbBook.getRecordCount());
			
			dbOrder.createRecord(ORDER_FIELD_NAMES, orderValues);
			dbOrder.createRecord(ORDER_FIELD_NAMES, orderValues2);
			assertEquals("Expect record to be 2", 2, dbOrder.getRecordCount());


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	@Test
	public void testCreateRecord() 
	{
		
		try {
			dbUsers.createRecord(USER_FIELD_NAMES, userValues);
			dbAccessLevel.createRecord(ACCESSLEVEL_FIELD_NAMES, accessLevelValues);
			dbCustomer.createRecord(CUSTOMER_FIELD_NAMES, customerValues);
			dbBook.createRecord(BOOK_FIELD_NAMES, bookValues);
			dbOrder.createRecord(ORDER_FIELD_NAMES, orderValues);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}
 
	@Test
	public void testUpdateRecord() 
	{
		
		String[] updatedFieldsAuthors = { "name", "born", "death"};
		String[] updatedValueAuthor = { "Jane Austen", "December 16, 1775", "July 18, 1817" };
		
		String[] updatedFieldsOrderStatus = { "orderStatus" };
		String[] updatedValuesOrderStatus = { "This is a new status" };
		
		try 
		{
			dbAuthor.createRecord(AUTHOR_FIELD_NAMES, authorValues);
			dbAuthor.updateRecord("1", updatedFieldsAuthors, updatedValueAuthor);
			assertEquals("Expect record to be 1", 1, dbAuthor.getRecordCount());
			
			dbOrderStatus.createRecord(ORDERSTATUS_FIELD_NAMES, orderStatusValues);
			dbOrderStatus.updateRecord("1", updatedFieldsOrderStatus, updatedValuesOrderStatus);
			assertEquals("Expect record to be 1", 1, dbOrderStatus.getRecordCount());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
 
	@Test
	public void testDeleteRecord() 
	{
		try 
		{
			dbCondition.createRecord(CONDITION_FIELD_NAMES, conditionValues);
			assertEquals("Expect record to be 1", 1, dbCondition.getRecordCount());
			dbCondition.deleteRecord("1");
			assertEquals("Expect record to be 0", 0, dbCondition.getRecordCount());

			dbGenre.createRecord(GENRE_FIELD_NAMES, genreValues);
			assertEquals("Expect record to be 1", 1, dbGenre.getRecordCount());
			dbGenre.deleteRecord("1");
			assertEquals("Expect record to be 0", 0, dbGenre.getRecordCount());


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
}
 
 
 

