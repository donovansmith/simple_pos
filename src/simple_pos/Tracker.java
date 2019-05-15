package simple_pos;

import java.util.HashMap;

public class Tracker {

    //keeping track of all sales
    HashMap<Integer,Sale> saleHashMap = new HashMap<>();

    //tracking sale id and incrementing it for every sale
    private int saleId = 0;
    private int receiptId = 0;

    //creating a singleton pattern for the Tracker
    private static Tracker instance = null;
    protected Tracker() {
        // Exists only to defeat instantiation.
    }
    public static Tracker getInstance() {
        if (instance == null) {
            instance = new Tracker();
            instance.saleId = 1;
        }
        return instance;
    }

    public void addSale(Sale sale){
        if(!saleHashMap.containsKey(sale.saleId))
            saleHashMap.put(sale.saleId, sale);
    }

    public int getSaleId() {
        saleId += 1;
        return saleId;
    }


    public int getReceiptId() {
        receiptId += 1;
        return receiptId;
    }
}
