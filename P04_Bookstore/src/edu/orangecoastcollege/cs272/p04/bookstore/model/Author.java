package edu.orangecoastcollege.cs272.p04.bookstore.model;

/**
 * <code>Author</code> represents an author a book, the author will have various fields 
 * such as an ID, name, birth date, and death date. The ID will be unique to the book's 
 * author, and will not be editable or deletable.
 * The authors name, birth date, and birth death will editable. The author will 
 * not be allowed to be deleted once the author has been entered.
 * 
 * @author Phil Davis
 *
 */
public class Author 
{
	private int mId;
	private String mName;
	private String mBorn;
	private String mDeath;
	
	/**
	 * Instantiates a <code>Author<code> given the required parameters, such as the 
	 * author's ID, the author's name, the author's birth date, the author's death date.
	 * 
	 * @param id The author's ID e.g. "15".
	 * @param name The author's name e.g. "David".
	 * @param bornThe author's birth date e.g. "May 10, 2017".
	 * @param death The death date ID e.g. "April 3, 1997".
	 */
	public Author(int id, String name, String born, String death) {
		super();
		mId = id;
		mName = name;
		mBorn = born;
		mDeath = death;
	}

	/**
	 * Gets the author's ID.
	 * @return mId The author's ID
	 */
	public int getId() {
		return mId;
	}

	/**
	 * Sets the author's ID.
	 * @param  mId The author's ID.
	 */
	public void setId(int id) {
		mId = id;
	}

	/**
	 * Gets the author's name.
	 * @return mName The author's name.
	 */
	public String getName() {
		return mName;
	}

	/**
	 * Sets the author's name
	 * @param name The author's name.
	 */
	public void setName(String name) {
		mName = name;
	}

	/**
	 * Gets the author's birth dates
	 * @return mBorn The author's birth date.
	 */
	public String getBorn() {
		return mBorn;
	}

	/**
	 * Sets the author's birth date.
	 * @param born The birth date of the author.
	 */
	public void setBorn(String born) {
		mBorn = born;
	}

	/**
	 * Gets the author's death date.
	 * @return mDeath The author's death date.
	 */
	public String getDeath() {
		return mDeath;
	}

	/**
	 * Sets the author's death date..
	 * @param death The author's death.
	 */
	public void setDeath(String death) {
		mDeath = death;
	}

	/**
	 * hashCode method provided by Java.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mBorn == null) ? 0 : mBorn.hashCode());
		result = prime * result + ((mDeath == null) ? 0 : mDeath.hashCode());
		result = prime * result + mId;
		result = prime * result + ((mName == null) ? 0 : mName.hashCode());
		return result;
	}

	/**
	 * equals method provided by Java.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (mBorn == null) {
			if (other.mBorn != null)
				return false;
		} else if (!mBorn.equals(other.mBorn))
			return false;
		if (mDeath == null) {
			if (other.mDeath != null)
				return false;
		} else if (!mDeath.equals(other.mDeath))
			return false;
		if (mId != other.mId)
			return false;
		if (mName == null) {
			if (other.mName != null)
				return false;
		} else if (!mName.equals(other.mName))
			return false;
		return true;
	}

	/**
	 * toString method provided by Java.
	 */
	@Override
	public String toString() {
		return "Author [mId=" + mId + ", mName=" + mName + ", mBorn=" + mBorn + ", mDeath=" + mDeath + "]";
	}
	
	
}