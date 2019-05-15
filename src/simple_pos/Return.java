package simple_pos;

import javax.swing.plaf.basic.BasicLabelUI;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Return extends Sale implements Transactions {

	protected int saleId;

	HashMap<Item, Integer> currentReturn = new HashMap<>();

	ArrayList<Receipt> receiptsGenerated = new ArrayList<>();

	Receipt receipt = null;
	Receipt oldReceipt = null;
	double total;
	double amountPaid;
	double balance;
	double change;
	
	Inventory inventory;

	Return(){
		this.inventory = new Inventory();
	}
	
	public void setReceipt(Receipt oldReceipt) {
		this.oldReceipt=oldReceipt;
	}
	public Receipt getReceipt() {
		return oldReceipt;
	}
	
	@Override
	public void addItem(Object item) {
		Item newItem = (Item)item;
		if (currentReturn.containsKey(newItem))
			currentReturn.put(newItem, currentReturn.get(item)+1);
		else
			currentReturn.put(newItem, 1);
        newItem.setQuantity(newItem.getQuantity()+1); //making sure we set item quantity to 1 to increase inventory quantity for this item by only 1
        inventory.addToInventory(newItem);
		
		//calculate total
		this.total+=newItem.getPrice();
	}

	@Override
    public void removeItem(Object item) {
        Item newItem = (Item)item;
        if(currentReturn.containsKey(newItem)){
            currentReturn.put(newItem, currentReturn.get(newItem)-1);
            if(currentReturn.get(newItem)<=0)
                currentReturn.remove(newItem);

    		newItem.setQuantity(newItem.getQuantity()-1);  //making sure we set item quantity to 1 to reduce inventory quantity for this item by only 1
    		inventory.removeFromInventory(newItem);
            //calculate total
            this.total-=newItem.getPrice();
        }
        else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Item not in return!");
            alert.setHeaderText(null);
            alert.setContentText("The item you're looking for is not in the current receipt.");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

	
	
	public Receipt getOldReceipt() {
		return oldReceipt;
	}
	
	
@Override
	public Receipt generateReceipt(int saleId, double payment, double moneyOwed){
		this.amountPaid = payment;
		this.change = moneyOwed;
		this.saleId=saleId;
		receipt = new Receipt(saleId, new Date(), null, total, currentReturn, payment, moneyOwed,0);
		return receipt;
	}
@Override
public String toString() {
	String toString="";
	for ( Map.Entry returnItem : currentReturn.entrySet()  ) {
         toString += ((Item) returnItem.getKey()).getName() + "      " + returnItem.getValue() +  "\n";
	}
	return toString;
}

//Getters and setters
public double getTotal() {
	return this.total;
}

}
