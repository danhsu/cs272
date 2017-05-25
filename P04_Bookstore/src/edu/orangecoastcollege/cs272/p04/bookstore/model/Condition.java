package edu.orangecoastcollege.cs272.p04.bookstore.model;

/**
 * <code>Condition</code> represents a book's condition. The condition
 * will have two fields, the ID and the condition. The ID will be unique
 * to the book's condition, and will not be editable or deletable. The 
 * condition will be a brief description of the condition of the book.
 * The condition will be editable, but will not be deletable once entered.
 * 
 * @author Phil Davis
 *
 */
public class Condition 
{
	private int mId;
	private String mCondition;
	
	/**
	 * Instantiates the <code>Condition</code> given the required parameters, such as the
	 * condition's id and the condition's condition.
	 * 
	 * @param id The ID of the condition.
	 * @param condition The description of the condition.
	 */
	public Condition(int id, String condition) {
		super();
		mId = id;
		mCondition = condition;
	}

	/**
	 * Gets the conditions ID.
	 * @return mId The condition's ID.
	 */
	public int getId() {
		return mId;
	}

	/**
	 * Sets the condition's ID.
	 * @param id The condition's ID.
	 */
	public void setId(int id) {
		mId = id;
	}

	/**
	 * Gets the condition's description.
	 * @return mCondition The condition's description.
	 */
	public String getCondition() {
		return mCondition;
	}

	/**
	 * Sets the condition's description.
	 * @param condition The conditions description.
	 */
	public void setCondition(String condition) {
		mCondition = condition;
	}
	
	/**
	 * hashCode method by Java.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mCondition == null) ? 0 : mCondition.hashCode());
		result = prime * result + mId;
		return result;
	}

	/**
	 * equals method by Java.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Condition other = (Condition) obj;
		if (mCondition == null) {
			if (other.mCondition != null)
				return false;
		} else if (!mCondition.equals(other.mCondition))
			return false;
		if (mId != other.mId)
			return false;
		return true;
	}

	/**
	 * toString method provided by Java.
	 */
	@Override
	public String toString() {
		return "Condition [mId=" + mId + ", mCondition=" + mCondition + "]";
	}	
}