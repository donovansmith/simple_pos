package simple_pos;

import javax.swing.plaf.basic.BasicSliderUI;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Iterator;
import java.util.Set;

public class Sale implements Transactions {

	protected int saleId;

	HashMap<Item, Integer> currentSale = new HashMap<>();

	ArrayList<Receipt> receiptsGenerated = new ArrayList<>();

	Receipt receipt = null;
	double total;
	double amountPaid;
	double balance;
	double change;
	
	Inventory inventory;

	Sale(){
		this.inventory = new Inventory();
	}
	
	@Override
	public void addItem(Object item) {
		Item newItem = (Item)item;
		if (currentSale.containsKey(newItem))
			currentSale.put(newItem, currentSale.get(item)+1);
		else
			currentSale.put(newItem, 1);
		newItem.setQuantity(newItem.getQuantity()-1);  //making sure we set item quantity to 1 to reduce inventory quantity for this item by only 1
		inventory.removeFromInventory(newItem);
		
		//calculate total
		this.total+=newItem.getPrice();
	}

	@Override
    public void removeItem(Object item) {
        Item newItem = (Item)item;
        if(currentSale.containsKey(newItem)){
            currentSale.put(newItem, currentSale.get(newItem)-1);
            if(currentSale.get(newItem)<=0)
                currentSale.remove(newItem);
            newItem.setQuantity(newItem.getQuantity()+1); //making sure we set item quantity to 1 to increase inventory quantity for this item by only 1
            inventory.addToInventory(newItem);
            
            //calculate total
            this.total-=newItem.getPrice();
        }
        else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Item not in sale!");
            alert.setHeaderText(null);
            alert.setContentText("The item you're looking for is not in the current sale.");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

	@Override
	public void cancel() {
		for (Map.Entry<Item, Integer> singleSale : currentSale.entrySet()) {
			Item item = singleSale.getKey();
			Integer quantity = singleSale.getValue();
			item.setQuantity(quantity);
			inventory.addToInventory(item);
		}
		currentSale = null;
	}


	@Override
	public double getPayment(double amountPaid, double balance) {
		this.amountPaid = amountPaid;
		this.balance = balance;
		return amountPaid;
	}

	@Override
	public Receipt generateReceipt(int saleId, double payment, double moneyOwed){
		this.amountPaid = payment;
		this.change = moneyOwed;
		this.saleId=saleId;
		receipt = new Receipt(saleId, new Date(), null, total, currentSale, payment, moneyOwed,0);
		return receipt;
	}

	
	@Override
	public double returnPayment(double amountReturned){
		return amountReturned;
	}
	
	//Getters and setters
	public double getTotal() {
		return this.total;
	}
	
	@Override
    public String toString() {
		String toString="";
		for ( Map.Entry saleItem : currentSale.entrySet()  ) {
             toString += ((Item) saleItem.getKey()).getName() + "      " + saleItem.getValue() +  "\n";
		}
		return toString;
	}
	

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}