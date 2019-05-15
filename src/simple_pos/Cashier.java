package simple_pos;
import java.util.ArrayList; 

public class Cashier {
	private String name;
	private ArrayList<Sale> drawer;
	
	Cashier(String name){
		this.name = name;
		this.drawer= new ArrayList<Sale>();
	}
	
	public void addToDrawer(Sale sale) {
		drawer.add(sale);
	}
	
	public void removeFromDrawer(Sale ret) {
		drawer.remove(ret);
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
