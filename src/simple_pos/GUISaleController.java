package simple_pos;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class GUISaleController {
	@FXML
	private TextArea inventoryTA;
	@FXML
	private TextArea currentSaleTA;
	@FXML
	private TextField ItemTF;
	@FXML
	private Button AddToSaleButton;
	
	@FXML
	public void initialize() {
		Main.getManagement().startSale();
		inventoryTA.setText(Main.getManagement().getMainInventory().toString());
	}
	
	@FXML
    private void EHAddToSaleButton(ActionEvent event) {    	
    	//https://code.makery.ch/blog/javafx-dialogs-official/
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Information Dialog");
    	alert.setHeaderText(null);
    	alert.setContentText("I have a great message for you!");

    	alert.showAndWait();
    	
    	
    	Main.getManagement().addToSale(ItemTF.getText());
    	ItemTF.setText("");
    	currentSaleTA.setText(Main.getManagement().getCurrentSale().toString());
    }

}
