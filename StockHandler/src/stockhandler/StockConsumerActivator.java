package stockhandler;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import stockmanagement.Product;
import stockmanagement.StockService;

public class StockConsumerActivator implements BundleActivator {

Scanner scanner = new Scanner(System.in);
	
	private ServiceReference<StockService> inventoryServiceRef;
    private StockService inventoryService;

	public void start(BundleContext context) throws Exception {
		
		String operation = "start";
		
		inventoryServiceRef = context.getServiceReference(StockService.class);
		inventoryService = context.getService(inventoryServiceRef);
		
		System.out.println("\n-------------------------------------");
		System.out.println("|      Welcome to Inventory App      |");
		System.out.println("-------------------------------------\n");
		
		while(!operation.equalsIgnoreCase("stop")) {
			
			System.out.println("What would you like to do?\n");
			System.out.println("a. Add a product.");
			System.out.println("b. Dispatch a product.");
			System.out.println("c. Remove a product.");
			System.out.println("d. Get product quantity.");
			System.out.println("e. Type \"stop\" to end.");
			System.out.println();
			
			System.out.print("Enter your choice: ");
			operation = scanner.next();
			
			if(operation.equalsIgnoreCase("a")) {
				
				double unitPrice = 0;
				int rLevel = 0;
				
			    System.out.print("Enter product name: ");
			    String productName = scanner.next();
			    
			    System.out.print("Enter quantity: ");
			    int quantity = scanner.nextInt();
			    
			    Product product = inventoryService.getProductByName(productName);
			    
			    if(product == null) {
			    	System.out.println("Enter Unit Price: ");
			    	unitPrice = scanner.nextDouble();
			    	
			    	System.out.println("Enter Reorder Level: ");
			    	rLevel = scanner.nextInt();
			    }

			    inventoryService.addProduct(productName, quantity, rLevel, unitPrice);
			    
			}else if(operation.equalsIgnoreCase("b")) {
				
				System.out.print("Enter product name: ");
			    String productName = scanner.next();
			    
			    System.out.print("Enter quantity: ");
			    int quantity = scanner.nextInt();

			    inventoryService.dispatchProduct(productName, quantity);
			    
			}else if(operation.equalsIgnoreCase("c")) {
				
				System.out.print("Enter product name: ");
			    String productName = scanner.next();
			    
			    inventoryService.deleteProduct(productName);
			    
			}else if(operation.equalsIgnoreCase("d")) {
				
				System.out.print("Enter product name: ");
			    String productName = scanner.next();
			    
			    System.out.println("");
			    System.out.println(productName + " : " + inventoryService.getQuantity(productName));
			       
			}else {
				break;
			}
		}
		
		System.out.println("\n------------------------------------------");
		System.out.println("|          Thank you for using our        |");
		System.out.println("|               Inventory App!            |");
		System.out.println("------------------------------------------");
		

	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop Inventory App....");
		context.ungetService(inventoryServiceRef);
	}

}
