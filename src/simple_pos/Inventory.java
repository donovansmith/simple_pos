package simple_pos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileReader;

public class Inventory {

	private int count = 0;
	private Item[] inventory = new Item[25];
	private ArrayList<Item> orders = new ArrayList<Item>();
	BufferedReader br = null;
	String line = "";
	
	Inventory(){
		try{
			br = new BufferedReader(new FileReader("Inventory.csv"));

			while ((line = br.readLine()) != null) {
				String item[] = line.split(",");
				inventory[count] = new Item(item[0], Double.parseDouble(item[1]), Integer.parseInt(item[2]), item[3], Integer.parseInt(item[4]));
				count++;
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(br !=null) {
				try {
					br.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void addToInventory(Item newItem) {
		for (int i = 0; i < inventory.length; i++) {
			
			if (inventory[i] != null && newItem.getName().equals(inventory[i].getName())) {
				inventory[i].setQuantity(newItem.getQuantity()+inventory[i].getQuantity());
			}
		}
	}
	
	
	public void removeFromInventory(Item inventoryItem) {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] != null && inventoryItem.getName().contentEquals(inventory[i].getName())) {
				int stockleft = inventoryItem.getQuantity();
				if(stockleft < 0) {
					System.out.println("Only have " + inventory[i].getQuantity() + " " + inventory[i].getName());
				}
				else {
					inventory[i].setQuantity(stockleft);
					
					//run threshold check here
					if (stockleft < inventory[i].getThreshold()) {
				        toOrder(inventory[i]);
				    }
				}
				return;
			}
		}
	}
	
	public Item searchInventory(String name) {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i].getName().equalsIgnoreCase(name) ) {
				return inventory[i];
			}
		}
		return null;
	}
	
    public void updateInventoryCSV() {
        try(PrintWriter writer = new PrintWriter(new File("Inventory.csv"))){
            StringBuilder s = new StringBuilder();
            
            int i = 0;
            while(inventory[i] != null) {
                s.append(inventory[i].getName() + ",");
                s.append(inventory[i].getPrice() + ",");
                s.append(inventory[i].getQuantity() + ",");
                s.append(inventory[i].getSupplier() + ",");
                s.append(inventory[i].getThreshold() + ",");
                s.append('\n');
                i++;
            }
            writer.write(s.toString());
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
	
    public void toOrder(Item name) {
        Item tempItem = name;
        tempItem.setQuantity(name.getThreshold()*3);
        if(orders.size() == 0) {
            orders.add(tempItem);
        }
        else {
            for(int x = 0;  x < orders.size(); x++) {
                if(orders.get(x).getName().equalsIgnoreCase(tempItem.getName())) {
                    return;
                }
            orders.add(tempItem);
            }
        }
        updateOrderCSV();
        return;
    }
    
    public void updateOrderCSV() {
        try(PrintWriter writer = new PrintWriter(new File("Order.csv"))){
            StringBuilder s = new StringBuilder();
            
            for(int i = 0; i < orders.size(); i++) {
                s.append(orders.get(i).getName() + ",");
                s.append(orders.get(i).getPrice() + ",");
                s.append(orders.get(i).getQuantity() + ",");
                s.append(orders.get(i).getSupplier() + ",");
                s.append(orders.get(i).getThreshold() + ",");
                s.append('\n');
            }
            writer.write(s.toString());
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
	@Override
    public String toString() {
        String s = null;
        for (int i = 0; i < inventory.length; i++) {
            if (i == 0){
                s = "";
            }
            if (inventory[i] != null) {
                s += "\n" + inventory[i];
            }
        }
        return s;
    }
}