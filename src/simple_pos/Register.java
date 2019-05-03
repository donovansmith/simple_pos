package simple_pos;

public class Register {
	
	private int registerNumber;
	private String user;
	private Arraylist<Sale> userDrawer;
	
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
