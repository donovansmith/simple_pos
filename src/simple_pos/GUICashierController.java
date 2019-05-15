package simple_pos;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class GUICashierController {
	@FXML
	private Button startSaleButton;	
	@FXML
	private Text headTimeT;	
	@FXML
	private Text headRegisterT;
	@FXML
	private Text headCashierT;
	
	@FXML
	public void initialize() {
		headCashierT.setText(Main.getManagement().getCurrentCashier().getName());
		headRegisterT.setText(Integer.toString(Main.getManagement().getCurrentRegister().getRegisterNumber()));
	}
	
    @FXML
    private void EHStartSaleButton(ActionEvent event) {
    	try {
			Main.grid.add((AnchorPane)FXMLLoader.load(getClass().getResource("GUISale.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Main.setPane(2);
    }
    
    @FXML
    private void EHLogoutButton(ActionEvent event) {    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Logout?");
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
    

}
