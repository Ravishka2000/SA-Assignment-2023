package garageservice_publisher;

import java.util.HashMap;
import java.util.Map;

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

}
