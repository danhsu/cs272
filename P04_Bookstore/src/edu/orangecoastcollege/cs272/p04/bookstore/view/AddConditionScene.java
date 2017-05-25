package edu.orangecoastcollege.cs272.p04.bookstore.view;

import edu.orangecoastcollege.cs272.p04.bookstore.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * <code>AddConditionScene</code> repersents the scene where the admin or
 * the manager can add a new condition. The scene uses only text field
 * where the book's condition can be inputed. The condition can be saved
 * with the save button. Thhe scene can be exited by the cancel button.
 * The condition is required in order to be inputed into the database. No
 * duplicate conditions are allowed within the database.
*/
public class AddConditionScene extends NavigationBar
{
	private static Controller controller = Controller.getInstance();

	@FXML
	private TextField conditionTF;

	@FXML
	private Label conditionErrorLabel;

	@FXML
	private Button saveButton;

	@FXML
	private Button cancelButton;

    /**
     * addCondition initializes the condition's condition
     * Checks to see if the condition text field is empty. If
     * The condition is empty, the program will display an error field.
     * The save button adds the condition to the database. If the
     * condition already exists in the database, an error will appear.
     *
     * @return addCondition object
    */
	@FXML
	public Object addCondition()
	{
		conditionErrorLabel.setVisible(false);

		String condition = conditionTF.getText();

		if(condition.isEmpty())
		{
			conditionErrorLabel.setVisible(true);
		}

		if (conditionErrorLabel.isVisible())
		{
	    	conditionErrorLabel.setText("Condition is required.");
	    	conditionErrorLabel.setVisible(true);
			return null;
		}

		String result = controller.addConditionControl(condition);
		if (result.equalsIgnoreCase("SUCCESS"))
	    {
			this.returnManageTables();
	    }
	    else
	    {
	    	conditionErrorLabel.setText(result);
	    	conditionErrorLabel.setVisible(true);
	    }
		return this;
	}


}




