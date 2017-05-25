package edu.orangecoastcollege.cs272.p04.bookstore.view;

import edu.orangecoastcollege.cs272.p04.bookstore.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * <code>AddCustomerScene</code> represents the scene where the admin or
 * the manager can add a new Customer. The scene uses various text fields
 * where the customer's name, home address, phone number, and email address
 * can be inputed. The customer can be saved with the save button. The
 * scene can be exited by the cancel button. The name is required
 * to input the customer into the database. No duplicate customers are
 * allowed within the database.
 *
 */
public class AddCustomerScene extends NavigationBar
{
	private static Controller controller = Controller.getInstance();

	@FXML
	private TextField nameTF;

	@FXML
	private TextField addressTF;

	@FXML
	private TextField phoneTF;

	@FXML
	private TextField emailTF;

	@FXML
	private Label customerNameErrorLabel;

	@FXML
	private Button saveButton;

	@FXML
	private Button cancelButton;


	/**
	 * AddCustomer initializes the customer's name, address, phone,
	 * and email. Checks to see if the name text field is empty. If
	 * The name is empty, the program will display an error field.
	 * The save button adds the customer to the database. If the
	 * customer already exists in the database, an error will appear.
	 *
	 * @return addCustomer object
	 */
	@FXML
	public Object addCustomer()
	{
	    customerNameErrorLabel.setVisible(false);

		String name = nameTF.getText();
		String address = addressTF.getText();
		String phone = phoneTF.getText();
		String email = emailTF.getText();

        if(name.isEmpty())
        {
            customerNameErrorLabel.setVisible(true);
        }

        if (customerNameErrorLabel.isVisible())
        {
            customerNameErrorLabel.setText("A name is required.");
            customerNameErrorLabel.setVisible(true);
            return null;
        }


		String result = controller.addCustomerControl(name, address, phone, email);
		if (result.equalsIgnoreCase("SUCCESS"))
		{
			this.returnCustomers();;
		}
		else
		{
		    customerNameErrorLabel.setText(result);
		    customerNameErrorLabel.setVisible(true);
		}

		return this;
	}


}


