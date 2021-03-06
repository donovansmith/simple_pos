package simple_pos;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
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


    	
    	if (Main.getManagement().login(usernameTF.getText(), passwordPF.getText()) ) {
			try {
				Main.grid.add((AnchorPane)FXMLLoader.load(getClass().getResource("GUICashier.fxml")));
				Main.setPane(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
    	}
    	else {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Incorrect Logon");
        	alert.setHeaderText(null);
        	alert.setContentText("Incorrect Username/Password");
        	alert.showAndWait();
    	}
    	
    }
}
