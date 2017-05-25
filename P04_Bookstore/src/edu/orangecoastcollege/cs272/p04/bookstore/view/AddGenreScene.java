package edu.orangecoastcollege.cs272.p04.bookstore.view;

import edu.orangecoastcollege.cs272.p04.bookstore.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * <code>AddGenreScene</code> represents the scene where the admin or
 * the manager can add a new genre. The scene uses only text field
 * where the book's genre can be inputed. The genre can be saved
 * with the save button. The scene can be exited by the cancel button.
 * The genre is required in order to be inputed into the database. No
 * duplicate genres are allowed within the database.
*/
public class AddGenreScene extends NavigationBar
{
	private static Controller controller = Controller.getInstance();

	@FXML
	private TextField genreTF;

	@FXML
	private Label genreErrorLabel;

	@FXML
	private Button saveButton;

	@FXML
	private Button cancelButton;

    /**
     * addGenre initializes the genre's genre. Checks to see if the
     * genre text field is empty. If the genre is empty, the
     * program will display an error field. The save button adds the
     * genre to the database. If the genre already exists in the
     * database, an error will appear.
     *
     * @return addGenre object
    */
	@FXML
	public Object addGenre()
	{
		genreErrorLabel.setVisible(false);

		String genre = genreTF.getText();

		if(genre.isEmpty())
		{
			genreErrorLabel.setVisible(true);
		}

		if (genreErrorLabel.isVisible())
		{
			genreErrorLabel.setText("Genre is required.");
	    	genreErrorLabel.setVisible(true);
			return null;
		}

		String result = controller.addGenreControl(genre);
		if (result.equalsIgnoreCase("SUCCESS"))
	    {
			this.returnManageTables();
	    }
	    else
	    {
	    	genreErrorLabel.setText(result);
	    	genreErrorLabel.setVisible(true);
	    }

		return this;
	}

}



