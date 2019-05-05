package simple_pos;

public class Management {
	private Inventory mainInventory;
	
	
	public Management() {
		this.mainInventory=new Inventory();

	}
	
	protected Inventory getMainInventory() {
		return mainInventory;
	}

}
