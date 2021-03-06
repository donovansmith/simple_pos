package simple_pos;

public class Item {
	private String name;
	private double price;
	private int quantity;
	private String supplier;
	private int threshold;
	
	Item(String name, double price, int quantity, String supplier, int threshold){
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.supplier = supplier;
		this.threshold = threshold;
	}
	
	//getters and setters 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	@Override
	public String toString() {
        return "Item Name: " + name + "\nItem Price: " + price + "\nQuantity Avalible: " + quantity + "\nSupplier: " + supplier
                + "\nOrder Threshold: " + threshold + "\n";
    }
}
