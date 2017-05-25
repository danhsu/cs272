package edu.orangecoastcollege.cs272.p04.bookstore.model;
 
/**
 * <code>User</code> represents the user
 * object.  It contains username, password,
 * position and accessLevel.  This class 
 * allows us to set the user's specifics
 * as well as retrieve them.
 * 
 * @author Brian
 *
 */
public class User 
{
	private int mId;
	private String mUsername;
	private String mPassword;
	private String mPosition;
	private int mAccessLevel;
	/**
	 * User constructor
	 * @param id
	 * @param username
	 * @param password
	 * @param position
	 * @param accessLevel
	 */
	public User(int id, String username, String password, String position, int accessLevel) {
		super();
		mId = id;
		mUsername = username;
		mPassword = password;
		mPosition = position;
		mAccessLevel = accessLevel;
	}
	/**
	 * retrieves the id
	 * @return the integer id
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
	 * retrieves the username
	 * @return the string username
	 */
	public String getUsername() {
		return mUsername;
	}
	/**
	 * sets the username
	 * @param username
	 */
	public void setUsername(String username) {
		mUsername = username;
	}
	/**
	 * retrieves the password
	 * @return string password
	 */
	public String getPassword() {
		return mPassword;
	}
	/**
	 * sets the password
	 * @param password
	 */
	public void setPassword(String password) {
		mPassword = password;
	}
	/**
	 * retrieves the position
	 * @return string position
	 */
	public String getPosition() {
		return mPosition;
	}
	/**
	 * sets the position 
	 * @param position
	 */
	public void setPosition(String position) {
		mPosition = position;
	}
	/**
	 * retrieves the accesslevel
	 * @return integer accessLevel
	 */
	public int getAccessLevel() {
		return mAccessLevel;
	}
	/**
	 * sets the accessLevel
	 * @param accessLevel
	 */
	public void setAccessLevel(int accessLevel) {
		mAccessLevel = accessLevel;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mAccessLevel;
		result = prime * result + mId;
		result = prime * result + ((mPassword == null) ? 0 : mPassword.hashCode());
		result = prime * result + ((mPosition == null) ? 0 : mPosition.hashCode());
		result = prime * result + ((mUsername == null) ? 0 : mUsername.hashCode());
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
		User other = (User) obj;
		if (mAccessLevel != other.mAccessLevel)
			return false;
		if (mId != other.mId)
			return false;
		if (mPassword == null) {
			if (other.mPassword != null)
				return false;
		} else if (!mPassword.equals(other.mPassword))
			return false;
		if (mPosition == null) {
			if (other.mPosition != null)
				return false;
		} else if (!mPosition.equals(other.mPosition))
			return false;
		if (mUsername == null) {
			if (other.mUsername != null)
				return false;
		} else if (!mUsername.equals(other.mUsername))
			return false;
		return true;
	}
	
	/**
	 * compiles everything into one string
	 */
	@Override
	public String toString() {
		return "User [mId=" + mId + ", mUsername=" + mUsername + ", mPassword=" + mPassword + ", mPosition=" + mPosition
				+ ", mAccessLevel=" + mAccessLevel + "]";
	}
	
	
}
 
 
 
