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
	
	@Override
	public String toString() {
		String s = this.registerNumber + "/n" + this.user + "/n";
		for(int d = 0; d < userDrawer.size(); d++) {
			for(int r = 0; r < userDrawer.get(d).receiptsGenerated.size(); r++) {
				s+= userDrawer.get(d).receiptsGenerated.get(r);
			}
		}
		return s;
	}



}
