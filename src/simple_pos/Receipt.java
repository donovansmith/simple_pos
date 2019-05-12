package simple_pos;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Receipt {

    private static final AtomicInteger count = new AtomicInteger(0);

    private int receiptId = 0;
    private Date purchaseDate;
    private Date returnDate;
    private Double total;
    private HashMap<Item,Integer> soldItems;
    private double amountPaid=0;
    private double balance = 0;
    private double amountReturned = 0;

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
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

    public Receipt(Date purchaseDate, Date returnDate, Double total, HashMap<Item, Integer> soldItems, double amountPaid,double balance, double amountReturned) {
        this.receiptId = receiptId;
        this.purchaseDate = purchaseDate;
        this.returnDate = returnDate;
        this.total = total;
        this.soldItems = soldItems;
        this.amountReturned = amountReturned;
    }



    @Override
    public String toString() {
        receiptId = count.incrementAndGet();
        return "Receipt{" +
                "receiptId=" + receiptId +
                ", purchaseDate=" + purchaseDate +
                ", returnDate=" + returnDate +
                ", total=" + total +
                ", soldItems=" + soldItems +
                ", amount Paid=" + amountPaid +
                ", balance left=" + balance +
                '}';
    }

}