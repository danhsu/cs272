package edu.orangecoastcollege.cs272.p04.bookstore.model;
 
/**
 * <code>AccessLevel<code> represents the access level of a user.
 * User has 4 access: 1 is for Administrator, it is one and only.
 * 2 is for Asst. Manager, 3 is for Salesperson, 4 is for Inactive User.
 * @author Brian
 * @version 1.0
 */
public class AccessLevel 
{
	private int mId;
	private int mAccessLevel;
	private String mDescription;
	
	/**
	 * AccessLevel Constructor creates a AccessLevel Object with the following
	 * @param id is user id
	 * @param accessLevel is user access level
	 * @param description is the description of the access level
	 */
	public AccessLevel(int id, int accessLevel, String description) {
		super();
		mId = id;
		mAccessLevel = accessLevel;
		mDescription = description;
	}
	/**
	 * Gets User ID
	 * @return user ID
	 */
	public int getId() {
		return mId;
	}
	/**
	 * Sets User ID
	 * @param id
	 */
	public void setId(int id) {
		mId = id;
	}
	/**
	 * Gets Access Level
	 * @return user's acccess level
	 */
	public int getAccessLevel() {
		return mAccessLevel;
	}
	/**
	 * Sets Access Level
	 * @param accessLevel
	 */
	public void setAccessLevel(int accessLevel) {
		mAccessLevel = accessLevel;
	}
	/**
	 * Gets the description of the access level
	 * @return
	 */
	public String getDescription() {
		return mDescription;
	}
	/**
	 * Sets the description of the access level
	 * @param description
	 */
	public void setDescription(String description) {
		mDescription = description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mAccessLevel;
		result = prime * result + ((mDescription == null) ? 0 : mDescription.hashCode());
		result = prime * result + mId;
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
		AccessLevel other = (AccessLevel) obj;
		if (mAccessLevel != other.mAccessLevel)
			return false;
		if (mDescription == null) {
			if (other.mDescription != null)
				return false;
		} else if (!mDescription.equals(other.mDescription))
			return false;
		if (mId != other.mId)
			return false;
		return true;
	}
	/**
	 * gathers all info on the access level and puts it
	 * under one string
	 * @return String of the access level
	 */
 
	@Override
	public String toString() {
		return "AccessLevel [mId=" + mId + ", mAccessLevel=" + mAccessLevel + ", mDescription=" + mDescription + "]";
	}
	
	
 
	
	
 
}
 
