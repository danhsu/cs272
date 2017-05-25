package edu.orangecoastcollege.cs272.p04.bookstore.view;

import edu.orangecoastcollege.cs272.p04.bookstore.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * <code>AddAuthorScene</code> represents the scene where the admin or
 * the manager can add a new Author. The scene uses various text fields
 * where the Author's name, date of birth, and date of death can be
 * inputed. The author can be saved with the save button. The scene
 * can be exited by the cancel button. The name is required to input the
 * Author into the database. No duplicate Authors are allowed within
 * the database.
*/
public class AddAuthorScene extends NavigationBar
{
	private static Controller controller = Controller.getInstance();

	@FXML
	private TextField nameTF;

	@FXML
	private TextField  dateOfBirthTF;

	@FXML
	private TextField dateOfDeathTF;

	@FXML
	private Label authorNameErrorLabel;

	@FXML
	private Button saveButton;

	@FXML
	private Button cancelButton;

	 /**
	  * addAuthor initializes the Author's name, date of birth, date
	  * of death. Checks to see if the name text field is empty. If
	  * The name is empty, the program will display an error field.
	  * The save button adds the author to the database. If the
	  * author already exists in the database, an error will apear.
	  *
	  * @return addAuthor object
     */
	@FXML
	public Object addAuthor()
	{
		authorNameErrorLabel.setVisible(false);

		String name = nameTF.getText();
		String dateOfBirth = dateOfBirthTF.getText();
		String dateOfDeath = dateOfDeathTF.getText();

		if(name.isEmpty())
		{
			authorNameErrorLabel.setVisible(true);
		}

		if (authorNameErrorLabel.isVisible())
		{
			authorNameErrorLabel.setText("A name is required.");
			authorNameErrorLabel.setVisible(true);
			return null;
		}

		String result = controller.addAuthorControl(name, dateOfBirth, dateOfDeath);
		if (result.equalsIgnoreCase("SUCCESS"))
	    {
			this.returnManageTables();

	    }
	    else
	    {
	    	authorNameErrorLabel.setText(result);
	    	authorNameErrorLabel.setVisible(true);
	    }
		return this;
	}

}



