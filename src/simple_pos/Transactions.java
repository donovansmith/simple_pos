package simple_pos;

public interface Transactions {
	void addItem(Object Item);
	void removeItem(Object Item);
	void cancel();
	double getPayment(double amountPaid, double balance);
	double returnPayment(double amountReturned);
	Receipt generateReceipt(int saleId, double payment, double moneyOwed);
}
