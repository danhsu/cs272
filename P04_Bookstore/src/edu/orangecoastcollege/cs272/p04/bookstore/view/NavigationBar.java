package edu.orangecoastcollege.cs272.p04.bookstore.view;

/**
 * <code>NavigationBar</code> used to navigate between scenes represented by
 * the breadcrumb style navigation bar on the header portion of the application GUI
 * 
 * ALSO used in many of the Buttons in the Scenes that only function to change Scenes
 * For example: ALL the Cancel Buttons, they only serve to change scenes, they do not have
 * any Controller-related methods, so we just use these simple actions instead of making new methods.
 * 
 * @author Danny Hsu
 * @version 1.0
 */
public abstract class NavigationBar 
{
	/**
	 * Return to Home Scene
	 */
	public void returnHome()
	{
		ViewNavigator.loadScene("Home", ViewNavigator.HOME_SCENE);
	}
	/**
	 * Return to Book Inventory Scene
	 */
	public void returnBookInventory()
	{
		ViewNavigator.loadScene("Book Inventory", ViewNavigator.BOOK_INVENTORY_SCENE);
	}
	/**
	 * Return to Manage Book Info Tables Scene
	 */
	public void returnManageTables()
	{
		ViewNavigator.loadScene("Manage Tables", ViewNavigator.MANAGE_TABLES_SCENE);
	}
	/**
	 * Return to Manage Customers Scene
	 */
	public void returnCustomers()
	{
		ViewNavigator.loadScene("Customers", ViewNavigator.MANAGE_CUSTOMERS_SCENE);

	}
	/**
	 * Return to Manage Order Scene
	 */
	public void returnOrders()
	{
		ViewNavigator.loadScene("Orders", ViewNavigator.MANAGE_ORDERS_SCENE);

	}
	/**
	 * Return to Manage Users Scene
	 */
	public void returnUsers()
	{
		ViewNavigator.loadScene("Users", ViewNavigator.MANAGE_USERS_SCENE);
	}
	/**
	 * Return to Create Order (1) Add Customer Scene
	 */
	public void returnOrder1AddCustomer()
	{
		ViewNavigator.loadScene("Order Step 1 Add Customer", ViewNavigator.ORDER_1_ADD_CUSTOMER);
	}
	/**
	 * Return to Create Order (2) Add Book Scene
	 */
	public void returnOrder2AddBook()
	{
		ViewNavigator.loadScene("Order Step 2 Add Book", ViewNavigator.ORDER_2_ADD_BOOK);
	}
	/**
	 * Return to Create Order (3) Review Order Scene
	 */
	public void returnOrder3Final()
	{
		ViewNavigator.loadScene("Order Step 3 Review Order", ViewNavigator.ORDER_3_FINAL_ORDER);
	}

}
