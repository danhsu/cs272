package edu.orangecoastcollege.cs272.p04.bookstore.model;

import java.util.ArrayList;

/**
 * <code>OrderInformation</code> is a container class to display Order Object Info by gathering
 * info from Customer object, User object, Order Status object
 * @author Danny Hsu
 * @version 1.0
 */
public class OrderInformation 
{
	private int orderId;
	private String username;
	private String customer;
	private double total;
	private String orderType;
	private String orderStatus;
	private ArrayList<String> bookTitleList; // Holds a list of book titles as part of this order
	
	/**
	 * OrderInformation constructor makes OrderInformation object with the following
	 * @param orderId is order ID
	 * @param username is the User's username
	 * @param customer is the Customer's name
	 * @param total is the total price of order
	 * @param orderType is the order type
	 * @param orderStatus is the order status
	 */
	public OrderInformation(int orderId, String username, String customer, double total, String orderType,
			String orderStatus) {
		super();
		this.orderId = orderId;
		this.username = username;
		this.customer = customer;
		this.total = total;
		this.orderType = orderType;
		this.orderStatus = orderStatus;
	}
	
	/**
	 * OrderInformation constructor makes OrderInformation object with the following
	 * @param orderId is order ID
	 * @param username is the User's username
	 * @param customer is the Customer's name
	 * @param total is the total price of order
	 * @param orderType is the order type
	 * @param orderStatus is the order status
	 * @param bookTitleList is a ArrayList of the String containing the list of book titles
	 */
	public OrderInformation(int orderId, String username, String customer, double total, String orderType,
			String orderStatus, ArrayList<String> bookTitleList) {
		super();
		this.orderId = orderId;
		this.username = username;
		this.customer = customer;
		this.total = total;
		this.orderType = orderType;
		this.orderStatus = orderStatus;
		this.bookTitleList = bookTitleList;
	}
	/**
	 * Get Order ID
	 * @return
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * Set Order ID
	 * @param orderId
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * Get User's username
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Set User's username
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Get Customer's Name
	 * @return
	 */
	public String getCustomer() {
		return customer;
	}
	/**
	 * Set Customer's Name
	 * @param customer
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	/**
	 * Get Order Total Price
	 * @return
	 */
	public double getTotal() {
		return total;
	}
	/**
	 * Set Order Total Price
	 * @param total
	 */
	public void setTotal(double total) {
		this.total = total;
	}
	/**
	 * Get Order Type
	 * @return
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * Set ORder Type
	 * @param orderType
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 * Get ORder Status
	 * @return
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/**
	 * Set ORder Status
	 * @param orderStatus
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * Get List of Book Titles
	 * @return
	 */
	public ArrayList<String> getBookTitleList() {
		return bookTitleList;
	}
	/**
	 * Set List of Book Titles
	 * @param bookTitleList
	 */
	public void setBookTitleList(ArrayList<String> bookTitleList) {
		this.bookTitleList = bookTitleList;
	}
	/**
	 * Print OrderInformation Object in following format
	 */
	@Override
	public String toString() {
		return "OrderInformation [orderId=" + orderId + ", username=" + username + ", customer=" + customer + ", total="
				+ total + ", orderType=" + orderType + ", orderStatus=" + orderStatus + ", bookTitleList="
				+ bookTitleList + "]";
	}


	
}
