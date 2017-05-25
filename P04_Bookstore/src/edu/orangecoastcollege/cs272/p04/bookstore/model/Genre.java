package edu.orangecoastcollege.cs272.p04.bookstore.model;

/**
 * <code>Genre</code> represents the books main theme. This will have 
 * two fields, the ID and the genre. The ID will be unique to the book's 
 * genre, and will not be editable or deletable. The genre will be 
 * a short description about the books main's theme. The genre will be 
 * editable, but will not be deletable once entered.
 * 
 * @author Phil Davis
 *
 */
public class Genre 
{
	private int mId;
	private String mGenre;
	
	/**
	 * Instantiates a <code>Genre</code> given the required parameters, such as
	 * the condition's ID and the condition's genre.
	 * 
	 * @param id The genres' ID e.g. "12".
	 * @param genre The genre e.g. "Science-Fiction".
	 */
	public Genre(int id, String genre) {
		super();
		mId = id;
		mGenre = genre;
	}

	/**
	 * Gets the condition's ID.
	 * @return mId The conditions ID.
	 */
	public int getId() {
		return mId;
	}

	/**
	 * Sets the ID of the condition.
	 * @param id The ID of the condition.
	 */
	public void setId(int id) {
		mId = id;
	}

	/**
	 * Gets the genre.
	 * @return mGenre The genre of the book.
	 */
	public String getGenre() {
		return mGenre;
	}

	/**
	 * Sets the genre.
	 * @param genre
	 */
	public void setGenre(String genre) {
		mGenre = genre;
	}

	/**
	 * hashCode method provided by Java.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mGenre == null) ? 0 : mGenre.hashCode());
		result = prime * result + mId;
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
		Genre other = (Genre) obj;
		if (mGenre == null) {
			if (other.mGenre != null)
				return false;
		} else if (!mGenre.equals(other.mGenre))
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
		return "Genre [mId=" + mId + ", mGenre=" + mGenre + "]";
	}
	
	
}
