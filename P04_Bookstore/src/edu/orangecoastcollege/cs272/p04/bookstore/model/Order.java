package edu.orangecoastcollege.cs272.p04.bookstore.model;

/**
 * <code>Order</code> is the sales order of the book(s)
 * @author Danny Hsu
 * @version 1.0
 */
public class Order 
{
	private int mId;
	private double mTotal;
	private int mCustomerId; // customer
	private int mUserId; // Original currentUser
	private boolean mOrderType;
	private int mOrderStatusId; // Order Status
	private boolean mValidOrder; // is this order finalized?
	
	/**
	 * Order constructor
	 * @param id id Order ID
	 * @param total is the sales total of Order
	 * @param customerId is customer ID
	 * @param userId is user ID
	 * @param orderType describes whether or not the order type is in-store or advance
	 * @param orderStatusId is the order status ID
	 * @param validOrder describes whether or not the order is valid
	 */
	public Order(int id, double total, int customerId, int userId, boolean orderType, int orderStatusId,
			boolean validOrder) {
		super();
		mId = id;
		mTotal = total;
		mCustomerId = customerId;
		mUserId = userId;
		mOrderType = orderType;
		mOrderStatusId = orderStatusId;
		mValidOrder = validOrder;
	}
	/**
	 * Get Order ID
	 * @return order ID
	 */
	public int getId() {
		return mId;
	}
	/**
	 * Set Order ID
	 * @param id
	 */
	public void setId(int id) {
		mId = id;
	}
	/**
	 * Get Order Total
	 * @return order Total price
	 */
	public double getTotal() {
		return mTotal;
	}
	/**
	 * Set Order Total
	 * @param total
	 */
	public void setTotal(double total) {
		mTotal = total;
	}
	/**
	 * Get Customer ID
	 * @return customer ID related to Order
	 */
	public int getCustomerId() {
		return mCustomerId;
	}
	/**
	 * Set Customer ID
	 * @param customerId
	 */
	public void setCustomerId(int customerId) {
		mCustomerId = customerId;
	}
	/**
	 * Get User ID
	 * @return user ID related to Order
	 */
	public int getUserId() {
		return mUserId;
	}
	/**
	 * Set User ID
	 * @param userId
	 */
	public void setUserId(int userId) {
		mUserId = userId;
	}
	/**
	 * Is the Order Type In-Store or Advance
	 * @return boolean value of the Order Type --> False is In-Store
	 */
	public boolean isOrderType() {
		return mOrderType;
	}
	/**
	 * Set Order Type
	 * @param orderType
	 */
	public void setOrderType(boolean orderType) {
		mOrderType = orderType;
	}
	/**
	 * Get Order Status ID
	 * @return the order status id related to this Order
	 */
	public int getOrderStatusId() {
		return mOrderStatusId;
	}
	/**
	 * Set Order Status ID
	 * @param orderStatusId
	 */
	public void setOrderStatusId(int orderStatusId) {
		mOrderStatusId = orderStatusId;
	}
	/**
	 * Is this a Valid Order
	 * When Valid Order big is set to True, the Order is valid
	 * @return
	 */
	public boolean isValidOrder() {
		return mValidOrder;
	}
	/**
	 * Set Valid ORder
	 * @param validOrder
	 */
	public void setValidOrder(boolean validOrder) {
		mValidOrder = validOrder;
	}
	/**
	 * Print Order Object in the following format
	 */
	@Override
	public String toString() {
		return "Order [mId=" + mId + ", mTotal=" + mTotal + ", mCustomerId=" + mCustomerId + ", mUserId=" + mUserId
				+ ", mOrderType=" + mOrderType + ", mOrderStatusId=" + mOrderStatusId + ", mValidOrder=" + mValidOrder
				+ "]";
	}

}
