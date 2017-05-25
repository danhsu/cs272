package edu.orangecoastcollege.cs272.p04.bookstore.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.p04.bookstore.controller.Controller;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Author;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Book;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Condition;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Genre;
import edu.orangecoastcollege.cs272.p04.bookstore.model.BookInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * <code>BookInventoryScene</code> is the GUI scene of the book inventory of the system.
 * It displays a list of books, by availability for sale and by sold.
 * Each Book contains its perspective information.
 * Also includes button to Add Book, to add new book to inventory
 * and button to Manage Book Info Tables, to edit additional info about books - author, condition, genre.
 * @author Danny Hsu
 * @version 1.0
 */
public class BookInventoryScene extends NavigationBar implements Initializable
{

	private static Controller controller = Controller.getInstance();
	
	@FXML
	ToggleGroup group = new ToggleGroup();
	
	@FXML
	private RadioButton availableBookRadioButton;
	
	@FXML
	private RadioButton soldBookRadioButton;
	
	@FXML
	private Label availableCountLabel;
	
	@FXML
	private Label soldCountLabel;
	
	@FXML
	private Button addBookButton;
	@FXML
	private Button manageBookInfoButton;
	
	@FXML
	private TextArea bookDescriptionTA;
	
	BookInformation selectedBook;
	
	@FXML
	private TableView<BookInformation> bookTV;
	
	ObservableList<Author> authorList = controller.getAllAuthors();
	ObservableList<Genre> genreList = controller.getAllGenre();
	ObservableList<Condition> conditionList = controller.getAllConditions();
	
	ObservableList<Book> allBookList = controller.getAllBookList(); // Not used, for test output only
	ObservableList<Book> soldBookList = controller.getAllSoldBookList();
	ObservableList<Book> availableBookList = controller.getAllAvailableBookList();
	ObservableList<BookInformation> mainList = FXCollections.observableArrayList();
	
	/**
	 * manageBookInfoAction() is for Manage Book Info Button to direct to that scene
	 */
	public void manageBookInfoAction()
	{
		this.returnManageTables();
	}
	
	/**
	 * addBookAction() is for the Add Book Button to direct to that scene
	 */
	public void addBookAction()
	{
		ViewNavigator.loadScene("Add Book", ViewNavigator.ADD_BOOK_SCENE);
	}

	/**
	 * populateAvailableBook() method populate the TableView with Available Books
	 * pull info from Author, Condition, Genre as well
	 * 
	 * Book whose Sold Bit -> false
	 */
	public void populateAvailableBook()
	{
		int id = 0;
		String title = null;
		String author = null;
		String pubYear = null;
		String edition = null;
		String genre = null;
		String condition = null;
		String description = null;
		double sales = 0;


		for (Book eachBook : availableBookList)
		{
			System.out.println(eachBook);
			id = eachBook.getId();
			title = eachBook.getTitle();
			pubYear = eachBook.getPubDate();
			edition = eachBook.getEdition();
			description = eachBook.getDescription();
			sales = eachBook.getSales();

			for (Author eachAuthor : authorList)
			{
				if (eachBook.getAuthorId() == eachAuthor.getId())
				{
					author = eachAuthor.getName();
				}
				else if (eachBook.getAuthorId() == 0)
				{
					author = null;
				}
			}
						
			for (Genre eachGen : genreList)
			{
				if (eachBook.getGenreId() == eachGen.getId())
				{
					genre = eachGen.getGenre();
				}
				else if (eachBook.getGenreId() == 0)
				{
					genre = null;
				}
			}
			
			for (Condition eachCon : conditionList)
			{
				if (eachBook.getConditionId() == eachCon.getId())
				{
					condition = eachCon.getCondition();				
				}
				else if (eachBook.getConditionId() == 0)
				{
					condition = null;
				}
			}

			BookInformation mainBook = new BookInformation(id, title, author, pubYear, edition, genre, condition, description, sales);
			mainList.add(mainBook);
		}
	}
	
	/**
	 * populateSoldBook() method populate the TableView with Sold Books
	 * pull info from Author, Condition, Genre as well
	 * 
	 * Sold Books are those that have been sold in the Create Order process
	 * Sold Bit -> set to True
	 */
	public void populateSoldBook()
	{
		int id = 0;
		String title = null;
		String author = null;
		String pubYear = null;
		String edition = null;
		String genre = null;
		String condition = null;
		String description = null;
		double sales = 0;

		for (Book eachBook : soldBookList)
		{
			System.out.println(eachBook);
			id = eachBook.getId();
			title = eachBook.getTitle();
			pubYear = eachBook.getPubDate();
			edition = eachBook.getEdition();
			description = eachBook.getDescription();
			sales = eachBook.getSales();

			for (Author eachAuthor : authorList)
			{
				if (eachBook.getAuthorId() == eachAuthor.getId())
				{
					author = eachAuthor.getName();
				}
				else if (eachBook.getAuthorId() == 0)
				{
					author = null;
				}
			}
						
			for (Genre eachGen : genreList)
			{
				if (eachBook.getGenreId() == eachGen.getId())
				{
					genre = eachGen.getGenre();
				}
				else if (eachBook.getGenreId() == 0)
				{
					genre = null;
				}
			}
			
			for (Condition eachCon : conditionList)
			{
				if (eachBook.getConditionId() == eachCon.getId())
				{
					condition = eachCon.getCondition();				
				}
				else if (eachBook.getConditionId() == 0)
				{
					condition = null;
				}
			}

			BookInformation mainBook = new BookInformation(id, title, author, pubYear, edition, genre, condition, description, sales);
			mainList.add(mainBook);
		}
	}
	
	/**
	 * selectedBookDescriptionTextAreaAction() method
	 * enable the user to select a book, and display its description in the TextArea
	 */
	public void selectedBookDescriptionTextAreaAction()
	{
		selectedBook = bookTV.getSelectionModel().getSelectedItem();
		bookDescriptionTA.clear();
		bookDescriptionTA.setText(selectedBook.getDescription());
	}
	
	/**
	 * availableBookRadioButtonAction() when toggled
	 * REFRESH TABLEVIEW and display Available Books
	 */
	@FXML
	public void availableBookRadioButtonAction()
	{
		bookDescriptionTA.clear();

		for ( int i = 0; i<bookTV.getItems().size(); i++) {
			bookTV.getItems().clear();
		}

		populateAvailableBook();
		bookTV.setItems(mainList);
	}
	
	/**
	 * soldBookRadioButtonAction() when toggled
	 * REFRESH TABLEVIEW and display Sold Books
	 */
	@FXML
	public void soldBookRadioButtonAction()
	{
		bookDescriptionTA.clear();

		for ( int i = 0; i<bookTV.getItems().size(); i++) {
			bookTV.getItems().clear();
		}
		
		populateSoldBook();
		bookTV.setItems(mainList);
	}

	/**
	 * initialize method initialized the scene with nodes and methods
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{

		availableCountLabel.setText(String.valueOf(availableBookList.size()));
		soldCountLabel.setText(String.valueOf(soldBookList.size()));

		availableBookRadioButtonAction();
		
		TableColumn<BookInformation, String> titleCol = new TableColumn<BookInformation, String>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("title"));
		
		TableColumn<BookInformation, String> authorCol = new TableColumn<BookInformation, String>("Author");
		authorCol.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("author"));

		TableColumn<BookInformation, String> pubYearCol = new TableColumn<BookInformation, String>("Published Date");
		pubYearCol.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("pubYear"));

		TableColumn<BookInformation, String> editionCol = new TableColumn<BookInformation, String>("Edition");
		editionCol.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("edition"));

		TableColumn<BookInformation, String> genreCol = new TableColumn<BookInformation, String>("Genre");
		genreCol.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("genre"));

		TableColumn<BookInformation, String> conditionCol = new TableColumn<BookInformation, String>("Condition");
		conditionCol.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("condition"));

		TableColumn<BookInformation, String> salesCol = new TableColumn<BookInformation, String>("Sales $");
		salesCol.setCellValueFactory(new PropertyValueFactory<BookInformation, String>("sales"));

		bookTV.getColumns().setAll(titleCol, authorCol, pubYearCol, editionCol, genreCol, conditionCol, salesCol);
		
		bookTV.setOnMouseClicked(e -> selectedBookDescriptionTextAreaAction());
		
		//TEST
		System.out.println(authorList.size());
		authorList.forEach(System.out::println);

		System.out.println(allBookList.size());
		allBookList.forEach(System.out::println);
	}
}
