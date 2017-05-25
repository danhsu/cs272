package edu.orangecoastcollege.cs272.p04.bookstore.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.p04.bookstore.controller.Controller;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Author;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Condition;
import edu.orangecoastcollege.cs272.p04.bookstore.model.Genre;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * <code>ManageTablesScene</code> is the GUI for managing the Book Information tables
 * they are: Author, Condition, Genre
 * These tables contains the information regarding each of these Book related information.
 * @author Phil Davis
 * @version 1.0
 */
public class ManageTablesScene extends NavigationBar implements Initializable
{
	@FXML
	private Button addAuthorButton;
	@FXML
	private Button addGenreButton;
	@FXML
	private Button addConditionButton;

	@FXML
	private TableView<Author> authorTV;
	
	@FXML
	private TableView<Genre> genreTV;
	
	@FXML
	private TableView<Condition> conditionTV;
	
	
	private static Controller controller = Controller.getInstance();
	
	ObservableList<Author> authorList = controller.getAllAuthors();
	ObservableList<Genre> genreList = controller.getAllGenre();
	ObservableList<Condition> conditionList = controller.getAllConditions();
	
	/**
	 * addAuthorAction() method for Add Author Button
	 * lead to AddAuthorScene
	 */
	public void addAuthorAction()
	{
		ViewNavigator.loadScene("Add Author", ViewNavigator.ADD_AUTHOR_SCENE);
	}
	/**
	 * addGenreAction() method for Add Genre Button
	 * lead to AddGenreScene
	 */
	public void addGenreAction()
	{
		ViewNavigator.loadScene("Add Genre", ViewNavigator.ADD_GENRE_SCENE);
	}
	/**
	 * addConditionAction() method for Add Condition Button
	 * lead to AddConditionScene
	 */
	public void addConditionAction()
	{
		ViewNavigator.loadScene("Add Condition", ViewNavigator.ADD_CONDITION_SCENE);
	}

	/**
	 * initialize method initialized the scene with nodes and methods
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// AUTHOR
		authorTV.setItems(authorList);	
		
		TableColumn<Author, String> nameCol = new TableColumn<Author, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Author, String>("name"));
		
		TableColumn<Author, String> birthCol = new TableColumn<Author, String>("Date of Birth");
		birthCol.setCellValueFactory(new PropertyValueFactory<Author, String>("born"));
		
		TableColumn<Author, String> deathCol = new TableColumn<Author, String>("Date of Death");
		deathCol.setCellValueFactory(new PropertyValueFactory<Author, String>("death"));

		authorTV.getColumns().setAll(nameCol, birthCol, deathCol);
		
		// GENRE
		genreTV.setItems(genreList);
		TableColumn<Genre, String> genreCol = new TableColumn<Genre, String>("Genre");
		genreCol.setCellValueFactory(new PropertyValueFactory<Genre, String>("genre"));
		genreTV.getColumns().setAll(genreCol);

		
		// CONDITION
		conditionTV.setItems(conditionList);
		TableColumn<Condition, String> conditionCol = new TableColumn<Condition, String>("Condition");
		conditionCol.setCellValueFactory(new PropertyValueFactory<Condition, String>("condition"));
		conditionTV.getColumns().setAll(conditionCol);
	}
}
