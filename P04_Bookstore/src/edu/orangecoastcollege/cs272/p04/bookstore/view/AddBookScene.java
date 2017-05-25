package edu.orangecoastcollege.cs272.p04.bookstore.view;

import java.net.URL;
import java.util.ResourceBundle;


import edu.orangecoastcollege.cs272.p04.bookstore.controller.Controller;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Author;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Condition;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * <code>AddBookScene</code> is the GUI for adding a new book to the Bookstore application
 * It will compile all the user input and update the database and the Java Objects accordingly.
 * @author Danny Hsu
 * @version 1.0
 */
public class AddBookScene extends NavigationBar implements Initializable
{
	private static Controller controller = Controller.getInstance();

	@FXML
	private TextField titleTF;
	@FXML
	private TableView<Author> authorTV;
	ObservableList<Author> authorList = controller.getAllAuthors();
	
	@FXML
	private TextField authorTF;
	@FXML
	private TextField birthTF;
	@FXML
	private TextField deathTF;
		
	@FXML
	private TextField pubDateTF;
	@FXML
	private TextField editionTF;
	@FXML
	private TextField salesPriceTF;
	@FXML
	private TextArea descriptionTA;

	@FXML
	private ComboBox<String> genreCB;
	@FXML
	private ComboBox<String> conditionCB;
		
	ObservableList<Condition> conditionList = controller.getAllConditions();
	ObservableList<String> conditionString = FXCollections.observableArrayList();
	ObservableList<Genre> genreList = controller.getAllGenre();
	ObservableList<String> genreString = FXCollections.observableArrayList();
	
	@FXML
	private Button addNewAuthorButton;
	@FXML
	private Button cancelButton;
	@FXML
	private Button saveButton;
	@FXML
	private Label errorLabel;
	
	// Author to add to Book
	Author selectedAuthor;
	
	/**
	 * initialize method initialized the scene with nodes and methods
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// Author TableView
		authorTV.setItems(authorList);
		TableColumn<Author, String> nameCol = new TableColumn<Author, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<Author, String> bornCol = new TableColumn<Author, String>("Date of Birth");
		bornCol.setCellValueFactory(new PropertyValueFactory<>("born"));
		TableColumn<Author, String> deathCol = new TableColumn<Author, String>("Date of Death");
		deathCol.setCellValueFactory(new PropertyValueFactory<>("death"));
		authorTV.getColumns().setAll(nameCol, bornCol, deathCol);
		
		// Condition ComboBox
		for (Condition c : conditionList)
		{
			String condition = c.getCondition();
			conditionString.add(condition);
		}
		FXCollections.sort(conditionString, String.CASE_INSENSITIVE_ORDER);
		conditionCB.setItems(conditionString);
		
		// Genre ComboBox
		for (Genre g : genreList)
		{
			String genre = g.getGenre();
			genreString.add(genre);
		}
		FXCollections.sort(genreString, String.CASE_INSENSITIVE_ORDER);
		genreCB.setItems(genreString);
		
		authorTV.setOnMouseClicked(e->selectedAuthorAction());
	}
	
	/**
	 * selectedAuthorAction() method is used to capture the selected author object
	 * fromt he TableView
	 */
	public void selectedAuthorAction()
	{
		selectedAuthor = authorTV.getSelectionModel().getSelectedItem();
		System.out.println("TESTAUTHOR " + selectedAuthor);
		System.out.println("TESTAUTHOR ID" + selectedAuthor.getId());

	}
	
	/**
	 * addNewAuthorAction() method is used when adding a new author during
	 * the process of adding a new book.
	 * This way, no need to leave this scene to go to Add New Author scene 
	 * just to add a new author.
	 * 
	 * Information is passed by Controller to the update Author database
	 * 
	 * Once the New Author is added, it is automatically selected as the 
	 * Author (and its associated ID) to be linked with the Book Object
	 * @return
	 */
	@FXML
	public Object addNewAuthorAction()
	{
		String name = authorTF.getText();
		String born = birthTF.getText();
		String died = deathTF.getText();
		
		if (authorTF.getText().isEmpty())
		{
			authorTF.setPromptText("Required");
			authorTF.setStyle("-fx-prompt-text-fill: red;");
			return null;
		}

		authorTF.setPromptText("Name");
		authorTF.setStyle("-fx-prompt-text-fill: gray;");
		authorTF.clear();
		birthTF.clear();
		deathTF.clear();
		
		String result = controller.addAuthorControl(name, born, died);
		
		if (result.equalsIgnoreCase("SUCCESS"))
		{
			authorTV.requestFocus();
			authorTV.getSelectionModel().select(authorList.size()-1);
			authorTV.getFocusModel().focus(authorList.size()-1);
			authorTV.scrollTo(authorList.size()-1);
			
			selectedAuthorAction();
			controller.setCurrentAuthor(selectedAuthor);

		}
		return this;
	}
	
	/**
	 * addBookAction() method takes all the information inputed by the user to create a New Book 
	 * (Controller pass info to update database and the ObservableList)
	 * @return
	 */
	@FXML
	public Object addBookAction()
	{
		if (salesPriceTF.getText().isEmpty() || titleTF.getText().isEmpty() || selectedAuthor==null)
		{
			errorLabel.setText("Title, Author, and Sales Price are required.\n"
					+ "If you added a new author, be sure to click \"Add New Author\".");
			errorLabel.setVisible(true);
			return null;
		}
		
		String title = titleTF.getText();
		String pubDate = pubDateTF.getText();
		String edition = editionTF.getText();
		double salesPrice = Double.parseDouble(salesPriceTF.getText());
		String description = descriptionTA.getText();
		boolean sold = false;
		

		String genre = genreCB.getSelectionModel().getSelectedItem();
		int genreId = 0;
		for (Genre g : genreList)
		{
			if (genre == g.getGenre())
			{
				genreId = g.getId();
			}
		}
		int conditionId = 0;
		String condition = conditionCB.getSelectionModel().getSelectedItem();
		for (Condition c : conditionList)
		{
			if (condition == c.getCondition())
			{
				conditionId = c.getId();
			}
		}
		
		int authorId = selectedAuthor.getId();
		
		String result = controller.addBookControl(title, pubDate, edition, salesPrice, description, sold, genreId, conditionId, authorId);
		if (result.equalsIgnoreCase("SUCCESS"))
	    {
			this.returnBookInventory();
	    }
	    else
	    {
	        errorLabel.setText(result);
	        errorLabel.setVisible(true);
	    }
				
		return this;
	}
	
}
