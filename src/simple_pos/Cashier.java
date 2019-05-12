package simple_pos;
import java.util.ArrayList; 

public class Cashier {
	private String name;
	private ArrayList<Sale> drawer;
	
	Cashier(String name){
		this.name = name;
	}
	
	public void newDrawer() {
		
	}
	
	public void addToDrawer(Sale sale) {
		drawer.add(sale);
	}
	
	public void removeFromDrawer(Sale ret) {
		drawer.remove(ret);
	}
	
	//function depends on definition of being "logged in"
	public void logOut() {
		
	}
	
	//return type will depend on Inventory type
	public void checkInventory() {
		
	}
	//getters and setters
   public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	public ArrayList<Sale> getDrawer() {
		return drawer;
	}

	public void setDrawer(ArrayList<Sale> drawer) {
		this.drawer = drawer;
	}


}
