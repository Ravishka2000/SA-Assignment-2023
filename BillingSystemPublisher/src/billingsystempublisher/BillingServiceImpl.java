package billingsystempublisher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class BillingServiceImpl implements BillingService{
	
	private final Map<String, Double> bill = new HashMap<>();
	
	private double apple = 100.00;
	private double orange = 80.00;
	private double grapes = 150.00;
	private double bread = 150.00;
	private double pen = 20.00;
	private double book = 200.00;
	
	@Override
	public void addPrice(String name, int quantity) {
		
		if (name.equalsIgnoreCase("apple")) {
			bill.put(name, quantity * apple);
		}	
		else if(name.equalsIgnoreCase("orange")) {
			bill.put(name, quantity * orange);
		}
		else if(name.equalsIgnoreCase("grapes")) {
			bill.put(name, quantity * grapes);
		}
		else if(name.equalsIgnoreCase("bread")) {
			bill.put(name, quantity * bread);
		}
		else if(name.equalsIgnoreCase("pen")) {
			bill.put(name, quantity * pen);
		}
		else if(name.equalsIgnoreCase("book")) {
			bill.put(name, quantity * book);
		}
		else {
			System.out.println("Item not found");
		}
		
		System.out.println("\n-----------------------");
		System.out.println("| Added Successfully! |");
		System.out.println("-----------------------");
	}

	@Override
	public void removePrice(String name, int quantity) {
		
       double newPrice = 0, currentPrice = 0;
		
		if (name.equalsIgnoreCase("apple")) {
			newPrice = quantity * apple;
			currentPrice = bill.getOrDefault(name, 0.0);
		}	
		else if(name.equalsIgnoreCase("orange")) {
			newPrice = quantity * orange;
			currentPrice = bill.getOrDefault(name, 0.0);
		}
		else if(name.equalsIgnoreCase("grapes")) {
			newPrice = quantity * grapes;
			currentPrice = bill.getOrDefault(name, 0.0);
		}
		else {
			System.out.println("Item not found");
		}
		
		
        bill.put(name, currentPrice - newPrice);
        
        System.out.println("\n-------------------------");
        System.out.println("| Deleted Successfully! |");
        System.out.println("-------------------------");
		
	}

	@Override
	public double getTotal() {
		double total = 0.0;
		for (Map.Entry<String, Double> entry : bill.entrySet()) {
            total +=  entry.getValue();
        }
		return total;	}

	@Override
	public void getTotalBill(double discount) {
		System.out.println("--------------------------------");
		System.out.println("|            Bill              |");
		System.out.println("--------------------------------");
		System.out.printf("| %-15s | %-10s |\n", "ITEM", "TOTAL");
		for (Map.Entry<String, Double> entry : bill.entrySet()) {
            System.out.printf("| %-15s | %-10s |\n", entry.getKey() , entry.getValue());
        }
		System.out.println("--------------------------------");
		System.out.printf("| %-15s | %-10s |\n", "Discount : " , discount);
		System.out.printf("| %-15s | %-10s |\n", "Total : " , (getTotal() - discount));
		System.out.println("--------------------------------");
	}

	@Override
	public double clacDiscount(int percentage) {
		return (percentage * getTotal()) / 100.0;
	}

	@Override
	public void printBill() {
		String filePath = "C:/Users/Sanduni Nimesha/Desktop/Final/bill.csv";
	    try (PrintWriter writer = new PrintWriter(new File(filePath))) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("Item,Total\n");
	        for (Map.Entry<String, Double> entry : bill.entrySet()) {
	            sb.append(entry.getKey()).append(",");
	            sb.append(entry.getValue()).append("\n");
	        }
	        writer.write(sb.toString());
	        System.out.println("\nBill Printed " + filePath + "");

	    } catch (FileNotFoundException e) {
	        System.out.println("Error creating file: " + e.getMessage());
	    }
	}

}
