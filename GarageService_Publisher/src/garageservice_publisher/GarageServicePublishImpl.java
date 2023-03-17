package garageservice_publisher;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GarageServicePublishImpl implements GarageServicePublish{

	private final Map<String, Integer> garage = new HashMap<>();
    private final Map<String, Integer> capacity = new HashMap<>();
    private final Map<String, Integer> parkingfee = new HashMap<>();

    public GarageServicePublishImpl() {
        garage.put("Car", 9);
        garage.put("Bike", 12);
        garage.put("ThreeWheeler", 7);

        capacity.put("Car", 40);
        capacity.put("Bike", 30);
        capacity.put("ThreeWheeler", 20);
        
        parkingfee.put("Car", 300);
        parkingfee.put("Bike", 100);
        parkingfee.put("ThreeWheeler", 200);
    }

	@Override
	public void addVehicle(String name, int quantity) {
		
		int currentQuantity = garage.getOrDefault(name, 0);
        int maxQuantity = capacity.getOrDefault(name, 0);

        if ((currentQuantity + quantity) > maxQuantity) {
            int remainingSpace = maxQuantity - currentQuantity;
            garage.put(name, maxQuantity);
            System.out.println("\nNot enough space for " + quantity + " " + name + "s. Only " + remainingSpace + " " + name + "s added.");
        } 
        else 
        {
            garage.put(name, currentQuantity + quantity);
            System.out.println("\n"+ quantity + " " + name + "s added to the garage.");
        }

        System.out.println("\nUpdated vehicle count => \n");
        
        System.out.println("--------------------------------");
        System.out.format("| %-15s | %-10s |\n", "Vehicle Name", "Quantity");
        System.out.format("|-----------------|------------|\n");
        for (Map.Entry<String, Integer> entry : garage.entrySet()) {
        	System.out.format("| %-15s | %-10d |\n", entry.getKey(), entry.getValue());
  
        }
        
        System.out.println("--------------------------------");
	}

	@Override
	public void removeVehicle(String name, int quantity) {
		
		
		int currentQuantity = garage.getOrDefault(name, 0);
        if (currentQuantity < quantity) {
            throw new IllegalArgumentException("\nInsufficient quantity in garage");
        }
        garage.put(name, currentQuantity - quantity);

        System.out.println("\nUpdated vehicle count => \n");
        System.out.println("--------------------------------");
        System.out.format("| %-15s | %-10s |\n", "Vehicle Name", "Quantity");
        System.out.format("|-----------------|------------|\n");
        for (Map.Entry<String, Integer> entry : garage.entrySet()) {
        	System.out.format("| %-15s | %-10d |\n", entry.getKey(), entry.getValue());
  
        }
        
        System.out.println("--------------------------------");
	}

	@Override
	public int getVehicles(String name) {
		
		return garage.getOrDefault(name, 0);
	}
	
	public void getFreeSlots() {
		
		
		System.out.println("\nFree slots for vehicles => \n");
		System.out.println("--------------------------------");
		System.out.format("| %-15s | %-10s |\n", "Vehicle Name", "Free space");
	    System.out.format("|-----------------|------------|\n");
        for (Map.Entry<String, Integer> entry : garage.entrySet()) {
            int freeSlots = capacity.get(entry.getKey()) - entry.getValue();
            System.out.format("| %-15s | %-10d |\n", entry.getKey(), freeSlots);
         
        }
        System.out.println("--------------------------------");
        
	}

	public void getParkingFee(String name, int mins) {
		
		int fee = parkingfee.getOrDefault(name, 0);
	    int additionalFee = 0;

	    if (mins > 30) {
	        additionalFee = ((mins - 30) / 30) * (fee / 2); // Calculate additional fee for every 30 minutes
	    }

	    int totalFee = fee + additionalFee;

	    System.out.println("\nParking fee for " + name + " for " + mins + " minutes =>  " + totalFee);
	    
	    System.out.println("\n -------------------------------");
	}

	public void printParkDetailsToCSV() {
		String filename = "/Users/pravi/Downloads" + "/garage_status.csv";
		File file = new File(filename);

		try {
		    PrintWriter pw = new PrintWriter(file);

		    // Write header
		    pw.write("Vehicle Name, Quantity, Capacity, Free Space, Parking Fee\n");

		    for (Map.Entry<String, Integer> entry : garage.entrySet()) {
		        String name = entry.getKey();
		        int quantity = entry.getValue();
		        int cap = capacity.getOrDefault(name, 0);
		        int freeSpace = cap - quantity;
		        int fee = parkingfee.getOrDefault(name, 0);

		        // Write data row
		        pw.write(name + "," + quantity + "," + cap + "," + freeSpace + "," + fee + "\n");
		    }

		    pw.close();
		    System.out.println("\nGarage status exported to " + filename);
		} catch (FileNotFoundException e) {
		    System.err.println("Error writing to CSV file: " + e.getMessage());
		}
		
	}
	
	public void importDetails() {

		String filename = "/Users/pravi/Downloads/garage_status.csv";
		File file = new File(filename);

	    try {
	        Scanner scanner = new Scanner(file);

	        // Skip the header row
	        scanner.nextLine();

	        System.out.println("-----------------------------------------------------------------------");
	        System.out.format("| %-15s | %-10s | %-10s | %-10s | %-10s |\n", "Item", "Quantity", "Capacity", "Free Space", "Parking Fee");
	        System.out.println("-----------------------------------------------------------------------");
	        
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] values = line.split(",");

	            // Parse the data from the CSV file
	            String name = values[0];
	            int quantity = Integer.parseInt(values[1]);
	            int capacity = Integer.parseInt(values[2]);
	            int freeSpace = Integer.parseInt(values[3]);
	            int parkingFee = Integer.parseInt(values[4]);

	            // Update the maps with the parsed data
	            garage.put(name, quantity);
	            this.capacity.put(name, capacity);
	            parkingfee.put(name, parkingFee);

	            // Print the imported details in a table
	            System.out.format("| %-15s | %-10d | %-10d | %-10d | %-10d |\n", name, quantity, capacity, freeSpace, parkingFee);


	        }
	        System.out.println("-----------------------------------------------------------------------");

	        scanner.close();
	        System.out.println("\nGarage status imported from " + file);

	    } catch (FileNotFoundException e) {
	        System.err.println("Error reading from CSV file: " + e.getMessage());
	    }
	}
}
