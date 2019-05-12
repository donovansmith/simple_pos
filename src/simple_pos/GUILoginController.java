package simple_pos;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class GUILoginController {
	//input fields
	@FXML
	private Text commentText;
	@FXML
	private TextField usernameTF;
	@FXML
	private PasswordField passwordPF;
	@FXML
	private Button loginButton;
	
	@FXML
	public void initialize() {
	}
	
    @FXML
    private void EHLoginButton(ActionEvent event) {
    	
    	//https://code.makery.ch/blog/javafx-dialogs-official/
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Information Dialog");
    	alert.setHeaderText(null);
    	alert.setContentText("I have a great message for you!");

    	alert.showAndWait();
    	Main.getManagement().login(usernameTF.getText(), passwordPF.getText());
    	Main.setPane(1);
    }
}
