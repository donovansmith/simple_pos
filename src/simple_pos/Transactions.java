package simple_pos;

public interface Transactions {
	void addItem(Object Item);
	void removeItem(Object Item);
	void cancel();
	double getPayment();
}
