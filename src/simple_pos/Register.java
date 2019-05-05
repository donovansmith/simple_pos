package simple_pos;

import java.util.ArrayList;

public class Register {
	
	private int registerNumber;
	private String user;
	private ArrayList<Sale> userDrawer;
	
	Register(int registerNumber){
		this.registerNumber = registerNumber;
	}
	
	Register(Cashier user){
		this.user = user.getName();
		this.userDrawer = user.getDrawer();		
	}
	
	Register(Cashier user, int registerNumber){
		this.user = user.getName();
		this.userDrawer = user.getDrawer();		
		this.registerNumber = registerNumber;
	}

}
