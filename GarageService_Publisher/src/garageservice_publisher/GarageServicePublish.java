package garageservice_publisher;

public interface GarageServicePublish {
	
	public void addVehicle(String name, int quantity);
	public void removeVehicle(String name, int quantity);
	public int getVehicles(String name);
	public void getFreeSlots();
	public void getParkingFee(String name, int mins);
	public void printParkDetailsToCSV();
	
}
