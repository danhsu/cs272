package edu.orangecoastcollege.cs272.p04.bookstore.model;
/**
 * <code>Book</code> is a book at the book store.
 * It contains info listed below.
 * @author Danny Hsu
 * @version 1.0
 */
public class Book 
{
	private int mId;
	private String mTitle;
	private String mPubDate;
	private String mEdition;
	private double mSales;
	private String mDescription;
	private boolean mSold;
	private int genreId;
	private int conditionId;
	private int authorId;
	
	/**
	 * Book Constructor
	 * @param id is book's ID
	 * @param title is book's Title
	 * @param pubDate is book's Published Date
	 * @param edition is book's Edition
	 * @param sales is book's sales price
	 * @param description is book's description
	 * @param sold describes whether or not a book has been sold yet
	 * @param genreId is book's genre ID -- Gets info from Genre Object
	 * @param conditionId is book's condition ID -- Gets info from Condition Object
	 * @param authorId is book's author ID -- Gets info from Author Object
	 */
	public Book(int id, String title, String pubDate, String edition, double sales, String description, boolean sold,
			int genreId, int conditionId, int authorId) {
		super();
		mId = id;
		mTitle = title;
		mPubDate = pubDate;
		mEdition = edition;
		mSales = sales;
		mDescription = description;
		mSold = sold;
		this.genreId = genreId;
		this.conditionId = conditionId;
		this.authorId = authorId;
	}
	/**
	 * Gets Book ID
	 * @return book id
	 */
	public int getId() {
		return mId;
	}
	/**
	 * Sets Book ID
	 * @param id
	 */
	public void setId(int id) {
		mId = id;
	}
	/**
	 * Gets Book Title
	 * @return book title
	 */
	public String getTitle() {
		return mTitle;
	}
	/**
	 * Sets Book Title
	 * @param title
	 */
	public void setTitle(String title) {
		mTitle = title;
	}
	/**
	 * Gets Published Date
	 * @return book published date
	 */
	public String getPubDate() {
		return mPubDate;
	}
	/**
	 * Sets Published Date
	 * @param pubDate
	 */
	public void setPubDate(String pubDate) {
		mPubDate = pubDate;
	}
	/**
	 * Gets Edition
	 * @return book edition
	 */
	public String getEdition() {
		return mEdition;
	}
	/**
	 * Sets Edition
	 * @param edition
	 */
	public void setEdition(String edition) {
		mEdition = edition;
	}
	/**
	 * Gets Sales Price
	 * @return book sales price
	 */
	public double getSales() {
		return mSales;
	}
	/**
	 * Sets Sales Price
	 * @param sales
	 */
	public void setSales(double sales) {
		mSales = sales;
	}
	/**
	 * Gets Description 
	 * @return book description
	 */
	public String getDescription() {
		return mDescription;
	}
	/**
	 * Sets Description
	 * @param description
	 */
	public void setDescription(String description) {
		mDescription = description;
	}
	/**
	 * Gets boolean of whether the book is sold
	 * true is sold, false is not sold
	 * @return
	 */
	public boolean isSold() {
		return mSold;
	}
	/**
	 * Sets boolean of sold
	 * @param sold
	 */
	public void setSold(boolean sold) {
		mSold = sold;
	}
	/**
	 * Gets Genre ID
	 * @return genre id
	 */
	public int getGenreId() {
		return genreId;
	}
	/**
	 * Sets Genre ID
	 * @param genreId
	 */
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	/**
	 * Gets Condition ID
	 * @return condition id
	 */
	public int getConditionId() {
		return conditionId;
	}
	/**
	 * Sets Condition ID
	 * @param conditionId
	 */
	public void setConditionId(int conditionId) {
		this.conditionId = conditionId;
	}
	/**
	 * Gets Author ID
	 * @return author id
	 */
	public int getAuthorId() {
		return authorId;
	}
	/**
	 * Sets Author ID
	 * @param authorId
	 */
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	/**
	 * Print Book Object in the following format
	 */
	@Override
	public String toString() {
		return "Book [mId=" + mId + ", mTitle=" + mTitle + ", mPubDate=" + mPubDate + ", mEdition=" + mEdition
				+ ", mSales=" + mSales + ", mDescription=" + mDescription + ", mSold=" + mSold + ", genreId=" + genreId
				+ ", conditionId=" + conditionId + ", authorId=" + authorId + "]";
	}
	
}
