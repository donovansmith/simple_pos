package simple_pos;

import java.util.ArrayList;

public class Register {
	
	private int registerNumber;
	private String user;
	private ArrayList<Sale> userDrawer;
	
	Register(int registerNumber){
		this.registerNumber = registerNumber;
	}
	

	public void setCashier(Cashier user){
		this.user = user.getName();
		this.userDrawer = user.getDrawer();		
	}
	
	public void addToDrawer(Sale sale) {
		this.userDrawer.add(sale);
	}
	
	public int getRegisterNumber() {
		return registerNumber;
	}
	
	@Override
	public String toString() {
		String s = null;
		if (this.user == null) {
			s = "Register number:" + this.registerNumber + "\n";
			s += "No user today\n";
		}
		else{
			s = "Register number:" + this.registerNumber + "\n" + "User: " + this.user + "\n" + "Drawer:\n";
			for (int d = 0; d < this.userDrawer.size(); d++) {
				s += this.userDrawer.get(d).toString() + "\n";
				s += "Total: " + this.userDrawer.get(d).getTotal() + "\n";
				s += "Payment: " + this.userDrawer.get(d).getAmountPaid() + "\n";
				s += "Change Given: " + this.userDrawer.get(d).getChange() + "\n";
			}
		}
		s += "\n";
		return s;
	}
}
