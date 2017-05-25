package edu.orangecoastcollege.cs272.p04.bookstore.model;
 
/**
 * <code>OrderStatus</code> describes a order's status for Advance Order.
 * There are 4 status:
 * 1 - To Be Picked Up - by customer
 * 2 - To Be Shipped - to customer
 * 3 - Picekd Up - by customer
 * 4 - Shipped - to customer
 * @author Brian
 * @version 1.0
 */
public class OrderStatus 
{
	private int mId;
	private String mOrderStatus;
	
	/**
	 * OrderStatus constructor creates OrderStatus object
	 * @param id is the OrderStatus ID
	 * @param orderStatus is the OrderStatus
	 */
	public OrderStatus(int id, String orderStatus) {
		super();
		mId = id;
		mOrderStatus = orderStatus;
	}
	/**
	 * retrieves the id
	 * @return id number
	 */
	public int getId() {
		return mId;
	}
	/**
	 * sets the id
	 * @param id
	 */
	public void setId(int id) {
		mId = id;
	}
	/**
	 * retrieves the string of order 
	 * status
	 * @return the String 
	 */
	public String getOrderStatus() {
		return mOrderStatus;
	}
	
	/**
	 * sets the order status
	 * @param orderStatus
	 */
	public void setOrderStatus(String orderStatus) {
		mOrderStatus = orderStatus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mId;
		result = prime * result + ((mOrderStatus == null) ? 0 : mOrderStatus.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderStatus other = (OrderStatus) obj;
		if (mId != other.mId)
			return false;
		if (mOrderStatus == null) {
			if (other.mOrderStatus != null)
				return false;
		} else if (!mOrderStatus.equals(other.mOrderStatus))
			return false;
		return true;
	}
	/**
	 * compiles a string of info
	 * @return a string
	 */
	@Override
	public String toString() {
		return "OrderStatus [mId=" + mId + ", mOrderStatus=" + mOrderStatus + "]";
	}
	
}
 
 
 
 
