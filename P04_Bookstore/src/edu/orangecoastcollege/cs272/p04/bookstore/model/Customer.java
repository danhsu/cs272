package edu.orangecoastcollege.cs272.p04.bookstore.model;
 
/**
 * <code>Customer</code> The customer is the buyer of books.
 * Each object is contains contact info for a customer of the bookstore.
 * @author Phil Davis
 * @version 1.0
 */
public class Customer 
{
	private int mId;
	private String mName;
	private String mAddress;
	private String mPhone;
	private String mEmail;
	
	/**
	 * Customer constructor Generates a new Customer object.
	 * @param id The id of the customer.
	 * @param name The name of the customer e.g. "Mike"
	 * @param address The address of the customer e.g. "2712, West Cliff, Newport Beach, CA, 92625"
	 * @param phone The phone number of the customer e.g. "949-754-2412"
	 * @param email The email address of the customer e.g. "Mike@hotmail.com"
	 */
	public Customer(int id, String name, String address, String phone, String email) {
		super();
		mId = id;
		mName = name;
		mAddress = address;
		mPhone = phone;
		mEmail = email;
	}
	
	/**
	 * getId Returns the customer's id.
	 * @return mId 
	 */
	public int getId() {
		return mId;
	}
 
	/**
	 * setId Sets the customer's id.
	 * @param id New id for the customer
	 */
	public void setId(int id) {
		mId = id;
	}
 
	/**
	 * getName Returns the customer's name.
	 * @return The name of the customer.
	 */
	public String getName() {
		return mName;
	}
 
	/**
	 * setName Sets a new name for the customer.
	 * @param name The new name for a customer.
	 */
	public void setName(String name) {
		mName = name;
	}
 
	/**
	 * getAddress Returns the customer's address.
	 * @return Returns the customer's address.
	 */
	public String getAddress() {
		return mAddress;
	}
 
	/**
	 * setAddress Sets the customers address.
	 * @param address The customers new address.
	 */
	public void setAddress(String address) {
		mAddress = address;
	}
 
	/**
	 * getPhone Returns the customer's phone number.
	 * @return The customer's phone number.
	 */
	public String getPhone() {
		return mPhone;
	}
 
	/**
	 * setPhone Sets the customer's phone number.
	 * @param phone Customer's new phone number.
	 */
	public void setPhone(String phone) {
		mPhone = phone;
	}
 
	/**
	 * getEmail returns the customer's email address.
	 * @return The customer's email address.
	 */
	public String getEmail() {
		return mEmail;
	}
	
	/**
	 * setEmail Sets the customers email address.
	 * @param email The customers new email address.
	 */
	public void setEmail(String email) {
		mEmail = email;
	}
 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mAddress == null) ? 0 : mAddress.hashCode());
		result = prime * result + ((mEmail == null) ? 0 : mEmail.hashCode());
		result = prime * result + mId;
		result = prime * result + ((mName == null) ? 0 : mName.hashCode());
		result = prime * result + ((mPhone == null) ? 0 : mPhone.hashCode());
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
		Customer other = (Customer) obj;
		if (mAddress == null) {
			if (other.mAddress != null)
				return false;
		} else if (!mAddress.equals(other.mAddress))
			return false;
		if (mEmail == null) {
			if (other.mEmail != null)
				return false;
		} else if (!mEmail.equals(other.mEmail))
			return false;
		if (mId != other.mId)
			return false;
		if (mName == null) {
			if (other.mName != null)
				return false;
		} else if (!mName.equals(other.mName))
			return false;
		if (mPhone == null) {
			if (other.mPhone != null)
				return false;
		} else if (!mPhone.equals(other.mPhone))
			return false;
		return true;
	}
 
	/**
	 * Print Customer object in following format
	 */
	@Override
	public String toString() {
		return "Customer [mId=" + mId + ", mName=" + mName + ", mAddress=" + mAddress + ", mPhone=" + mPhone
				+ ", mEmail=" + mEmail + "]";
	}

	
	
}
 
 
