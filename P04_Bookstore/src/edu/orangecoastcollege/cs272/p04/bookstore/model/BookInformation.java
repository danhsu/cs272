package edu.orangecoastcollege.cs272.p04.bookstore.model;

/**
 * <code>BookInformation</code> is a container class to display Book Object info by gathering 
 * info from Author object, Condition object, Genre object together to present the complete
 * info regarding a book
 * @author Danny Hsu
 * @version 1.0
 */
public class BookInformation 
{
	private int id;
	private String title;
	private String author;
	private String pubYear;
	private String edition;
	private String genre;
	private String condition;
	private String description;
	private double sales;
	
	/**
	 * BookInformation constructor to make BookInformation object
	 * @param id is the book's id
	 * @param title is the book's title
	 * @param author is the book's author
	 * @param pubYear is the published date (not just year, didn't have time to change the variable name)
	 * @param edition is the book's edition
	 * @param genre is the genre
	 * @param condition is the condition
	 * @param description is the description
	 * @param sales is the sales price
	 */
	public BookInformation(int id, String title, String author, String pubYear, String edition, String genre,
			String condition, String description, double sales) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.pubYear = pubYear;
		this.edition = edition;
		this.genre = genre;
		this.condition = condition;
		this.description = description;
		this.sales = sales;
	}

	/**
	 * BookInformation constructor to make BookInformation object
	 * @param id is book ID
	 * @param title is book title
	 * @param author is the author
	 * @param sales is the sales price
	 */
	public BookInformation(int id, String title, String author, double sales)
	{
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.sales = sales;
	}
	
	/**
	 * BookInformation constructor to make BookInformation object
	 * @param id is the book's ID
	 */
	public BookInformation(int id)
	{
		super();
		this.id = id;
	}

	/**
	 * Gets Book ID
	 * @return Book ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets Book ID
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets Book Title
	 * @return book title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets Book Title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets Author Name
	 * @return author's name
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets Author Name
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets Published Date
	 * @return book's published date
	 */
	public String getPubYear() {
		return pubYear;
	}

	/**
	 * Sets Published Date
	 * @param pubYear
	 */
	public void setPubYear(String pubYear) {
		this.pubYear = pubYear;
	}

	/**
	 * Gets Book's Edition
	 * @return book's edition
	 */
	public String getEdition() {
		return edition;
	}

	/**
	 * Sets Book's Edition
	 * @param edition
	 */
	public void setEdition(String edition) {
		this.edition = edition;
	}

	/**
	 * Gets Book's Genre
	 * @return book's genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Sets Book's Genre
	 * @param genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Gets Book's Condition
	 * @return 
	 */
	public String getCondition() {
		return condition;
	}

	/** 
	 * Sets Book's Condition
	 * @param condition
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * Gets Book's Description
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets Book's Description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets Book's sales price
	 * @return
	 */
	public double getSales() {
		return sales;
	}

	/**
	 * Sets Book's sales price
	 * @param sales
	 */
	public void setSales(double sales) {
		this.sales = sales;
	}

	/**
	 * Print BookInfomraiton Object in the following format
	 */
	@Override
	public String toString() {
		return "BookInformation [id=" + id + ", title=" + title + ", author=" + author + ", pubYear=" + pubYear
				+ ", edition=" + edition + ", genre=" + genre + ", condition=" + condition + ", description="
				+ description + ", sales=" + sales + "]";
	}

}
