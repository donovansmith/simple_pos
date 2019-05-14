package simple_pos;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;


public class GUISaleController {
	@FXML
	private TextArea inventoryTA;
	@FXML
	private TextArea currentSaleTA;
	@FXML
	private TextField ItemTF;
	@FXML
	private TextField TotalTF;
	@FXML
	private Button AddToSaleButton;
	
	//Others
	NumberFormat currencyFormat;
	
	@FXML
	public void initialize() {
		Main.getManagement().startSale();
		inventoryTA.setText(Main.getManagement().getMainInventory().toString());
		currencyFormat = NumberFormat.getCurrencyInstance(Locale.US); 
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
    	TotalTF.setText(currencyFormat.format(Main.getManagement().getCurrentSale().getTotal()));
    }

    @FXML
    private void EHLogoutButton(ActionEvent event) {    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Are you sure you want to logout?");
    	alert.setHeaderText(null);
    	alert.setContentText("Are you sure you want to logout?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
//    		if (Main.grid.size()!= 2) {
//    			try {
//    				for (int i=2; i<=Main.grid.size(); i++)
//        			Main.grid.remove(i);
//        		} catch (Exception e) {
//        			e.printStackTrace();
//        		}
//    		}        	
        	Main.setPane(0);
    	}
    }
    
    @FXML
    private void EHCompleteSaleButton(ActionEvent event) {    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Complete Sale?");
    	alert.setHeaderText(null);
    	alert.setContentText("Are you sure you want to complete the sale?");
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	Main.receiptPopup();

    	if (result.get() == ButtonType.OK){
//    		if (Main.grid.size()!= 2) {
//    			try {
//    				for (int i=2; i<=Main.grid.size(); i++)
//        			Main.grid.remove(i);
//        		} catch (Exception e) {
//        			e.printStackTrace();
//        		}
//    		}        	
        	Main.setPane(1);
    	}
    }
}
