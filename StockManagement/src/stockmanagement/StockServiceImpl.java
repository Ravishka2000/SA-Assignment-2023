package stockmanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class StockServiceImpl implements StockService{
	
	private final List<Product> inventory = new ArrayList<>();
	private Product product;
	
	public StockServiceImpl() {	
		inventory.add(new Product("Pens", 35, 100, 20));
		inventory.add(new Product("Pensils", 25, 200, 50));
		inventory.add(new Product("Papers", 15, 500, 100));
	}
	
	@Override
	public Product getProductByName(String name) {
	    for (Product product : inventory) {
	        if (product.getName().equalsIgnoreCase(name)) {
	            return product;
	        }
	    }
	    return null;
	}

	@Override
	public void addProduct(String name, int quantity, int reorderLevel, double unitPrice) {
		
		product = getProductByName(name);
		
		if(product == null) {
			inventory.add(new Product(name, unitPrice, quantity, reorderLevel));			
		}else {
			int currentQuantity = product.getQuantity();
			product.setQuantity(currentQuantity + quantity);
		}
		
        
		System.out.println("\n------------------------");
		System.out.println("|   Updated Inventory   |");
		System.out.println("------------------------\n");
		
		System.out.println("------------------------------------------------------------------");
		System.out.printf("| %-15s | %-10s | %-13s | %-15s |\n", "Product Name", "In Stock", "Unit Price", "Reorder Level");
		System.out.println("------------------------------------------------------------------");
		for (Product pro : inventory) {
		    System.out.printf("| %-15s | %-10d | Rs.%-10s | %-15s |\n", pro.getName(), pro.getQuantity(), pro.getUnitPrice(), pro.getReOrderLevel());
		}
		System.out.println("------------------------------------------------------------------\n");
	}

	@Override
	public void dispatchProduct(String name, int quantity) {
		
		int currentQuantity = 0;
		
		product = getProductByName(name);
		
		if(product == null) {
			System.out.println("Product does not exists.");
		}else {
			currentQuantity = product.getQuantity();
		}
		
        if (currentQuantity < quantity) {
        	System.out.println("\nInsufficient quantity in inventory");
        }else {
        	product.setQuantity(currentQuantity - quantity);
            
        	System.out.println("\n------------------------");
    		System.out.println("|   Updated Inventory   |");
    		System.out.println("------------------------\n");
    		
    		System.out.println("------------------------------------------------------------------");
    		System.out.printf("| %-15s | %-10s | %-13s | %-15s |\n", "Product Name", "In Stock", "Unit Price", "Reorder Level");
    		System.out.println("------------------------------------------------------------------");
    		for (Product pro : inventory) {
    		    System.out.printf("| %-15s | %-10d | Rs.%-10s | %-15s |\n", pro.getName(), pro.getQuantity(), pro.getUnitPrice(), pro.getReOrderLevel());
    		}
    		System.out.println("------------------------------------------------------------------\n");
        }
	}

	@Override
	public int getQuantity(String name) {
		product = getProductByName(name);
		
		if(product == null) {
			System.out.println("Product does not exists.");
			return 0;
		}else {
			return product.getQuantity();
		}
	}


	@Override
	public boolean deleteProduct(String name) {
		int index = -1;
	    for (int i = 0; i < inventory.size(); i++) {
	        if (inventory.get(i).getName().equalsIgnoreCase(name)) {
	            index = i;
	            break;
	        }
	    }
	    if (index >= 0) {
	    	String temp = inventory.get(index).getName();
	        inventory.remove(index);
	        System.out.println("");
	        System.out.println(temp + " successfully deleted.");
	        
	        System.out.println("\n------------------------");
			System.out.println("|   Updated Inventory   |");
			System.out.println("------------------------\n");
			
			System.out.println("------------------------------------------------------------------");
			System.out.printf("| %-15s | %-10s | %-13s | %-15s |\n", "Product Name", "In Stock", "Unit Price", "Reorder Level");
			System.out.println("------------------------------------------------------------------");
			for (Product pro : inventory) {
			    System.out.printf("| %-15s | %-10d | Rs.%-10s | %-15s |\n", pro.getName(), pro.getQuantity(), pro.getUnitPrice(), pro.getReOrderLevel());
			}
			System.out.println("------------------------------------------------------------------\n");
	        
	        return true;
	    }
	    return false;
	}
	
	@Override
	public void printInventoryToCSV() {
	    String filePath = "/Users/ravishkadulshan/desktop/inventory.csv";
	    try (PrintWriter writer = new PrintWriter(new File(filePath))) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("Product,Unit Price,Quantity,Reorder Level\n");
	        for (Product product : inventory) {
	            sb.append(product.getName()).append(",");
	            sb.append(product.getUnitPrice()).append(",");
	            sb.append(product.getQuantity()).append(",");
	            sb.append(product.getReOrderLevel()).append("\n");
	        }
	        writer.write(sb.toString());
	        System.out.println("\nℹ️ Inventory details written to " + filePath + " ℹ️");

	    } catch (FileNotFoundException e) {
	        System.out.println("Error creating file: " + e.getMessage());
	    }
	}

	
	
	
}
