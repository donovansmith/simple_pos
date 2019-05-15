package simple_pos;

public interface Transactions {
	void addItem(Object Item);
	void removeItem(Object Item);
	void cancel();
	//boolean makeSale();
	boolean makeReturn(Object item);
	double getPayment(double amountPaid, double balance);
	void requestCash();
	double returnPayment(double amountReturned);
	Receipt generateReceipt(int saleId, double payment, double moneyOwed);
}
