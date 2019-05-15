package simple_pos;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;


public class GUIReturnController {
	@FXML
	private TextArea OldReceiptTA;
	@FXML
	private TextArea receiptsTA;
	@FXML
	private TextField ItemTF;
	@FXML
	private TextField TotalTF;
	@FXML
	private Button AddToSaleButton;
	@FXML
	private Button removeFromSaleButton;	
	@FXML
	private Text headTimeT;	
	@FXML
	private Text headRegisterT;
	@FXML
	private Text headCashierT;
	
	//Others
	NumberFormat currencyFormat;
	
	@FXML
	public void initialize() {
		currencyFormat = NumberFormat.getCurrencyInstance(Locale.US); 
		headCashierT.setText(Main.getManagement().getCurrentCashier().getName());
		headRegisterT.setText(Integer.toString(Main.getManagement().getCurrentRegister().getRegisterNumber()));
		headTimeT.setText(Management.getTime());
		
		Main.receiptListPopup(Main.getManagement().getReceiptHeaders());

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText(null);
		dialog.setContentText("Please enter receipt ID");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    Main.getManagement().startReturn(Integer.parseInt(result.get()));
		    OldReceiptTA.setText(Main.getManagement().getCurrentReturn().getReceipt().toString());
		    System.out.println(Integer.parseInt(result.get()));
		    System.out.println(Main.getManagement().getCurrentReturn().getReceipt().toString());
		} else {
			Main.setPane(1);
		}
	}
	
	@FXML
    private void EHAddToReturnButton(ActionEvent event) {    	 	
    	Main.getManagement().addToReturn(ItemTF.getText());
    	ItemTF.setText("");
    	receiptsTA.setText(Main.getManagement().getCurrentReturn().toString());
    	TotalTF.setText(currencyFormat.format(Main.getManagement().getCurrentReturn().getTotal()));
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
    private void EHCompleteReturn(ActionEvent event) {    	
    	String moneyOwedString=null;
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Complete Sale?");
    	alert.setHeaderText(null);
    	alert.setContentText("Are you sure you want to complete the return?");
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	   	
    	
    	if (result.get() == ButtonType.OK){
    		TextInputDialog dialog = new TextInputDialog();
    		dialog.setTitle("Recieve Payment");
    		dialog.setHeaderText(null);
    		dialog.setContentText("Amount Recieved: ");

    		// Traditional way to get the response value.
    		Optional<String> result1 = dialog.showAndWait();
    		if (result1.isPresent()){
    			moneyOwedString=Main.getManagement().completeSale(Double.parseDouble(result1.get()));
    			Alert alertChange = new Alert(AlertType.INFORMATION);
    			alertChange.setTitle("Change");
    			alertChange.setHeaderText(null);
    			alertChange.setContentText("Return Change Amount: " + moneyOwedString );
    			alertChange.showAndWait();
    			Main.receiptPopup(Main.getManagement().getReceipts().toString()); 
    			
    		}  		
        	receiptsTA.setText("");
        	OldReceiptTA.setText("");
        Main.setPane(1);
    	}
    }
    
    @FXML
    private void EHRemoveReturnButton(ActionEvent event) {    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Remove From Sale?");
    	alert.setHeaderText(null);
    	alert.setContentText("Are you sure you want to remove from the sale?");
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	Main.getManagement().removeFromReturn(ItemTF.getText());
    	ItemTF.setText("");
    	receiptsTA.setText(Main.getManagement().getCurrentReturn().toString());
    	TotalTF.setText(currencyFormat.format(Main.getManagement().getCurrentReturn().getTotal()));
    }
    
    @FXML
    private void EHCancelReturn(ActionEvent event) {    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Cancel Return?");
    	alert.setHeaderText(null);
    	alert.setContentText("Are you sure you want to cancel return?");
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
    		Main.setPane(1);
    	}
    }
    

}
