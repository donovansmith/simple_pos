package simple_pos;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Receipt {

    private int receiptId = 0;
    private Date purchaseDate;
    private Date returnDate;
    private Double total;
    private HashMap<Item,Integer> soldItems;
    private double amountPaid;
    private String balance;
    private double amountReturned = 0;
    private int saleId = 0;


    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public double getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(double amountReturned) {
        this.amountReturned = amountReturned;
    }


    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public HashMap<Item, Integer> getSoldItems() {
        return soldItems;
    }

    public void setSoldItems(HashMap<Item, Integer> soldItems) {
        this.soldItems = soldItems;
    }

    public Receipt(int saleId, Date purchaseDate, Date returnDate, Double total, HashMap<Item, Integer> soldItems, double amountPaid,double balance, double amountReturned) {
        this.purchaseDate = purchaseDate;
        this.returnDate = returnDate;
        this.total = total;
        this.soldItems = soldItems;
        this.amountReturned = amountReturned;
        this.saleId = saleId;
        this.receiptId = saleId;
        this.amountPaid=amountPaid;
        
        DecimalFormat df = new DecimalFormat("#.##");
        this.balance= df.format(balance);
    }

    @Override
    public String toString() {
		String toString="Receipt ID: " + receiptId + '\n' +
                "Purchase Date: " + purchaseDate + '\n' + 
                "------------------" + '\n';
                
		for ( Map.Entry saleItem : soldItems.entrySet()  ) {
             toString += ((Item) saleItem.getKey()).getName() + "      " + saleItem.getValue() +  "\n";
		}
        toString+="------------------" + '\n' +
                "Total: " + total + '\n' + 
                "Amount Paid: " + amountPaid + '\n' +
                "Change: " + balance;                
        return toString;
    }

    public String headerToString() {
		String toString="Receipt ID: " + receiptId + '\n' +
                "Purchase Date: " + purchaseDate + '\n';             
        return toString;
    }
}
