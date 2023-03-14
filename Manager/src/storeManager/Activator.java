package storeManager;

import java.util.HashMap;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import employeepublisher.Employee;
import employeepublisher.ServicePublisher;

public class Activator implements BundleActivator {

	

	ServiceReference serviceReference;

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Start Subscriber Service");
		serviceReference = bundleContext.getServiceReference(ServicePublisher.class.getName());
		ServicePublisher servicePublish = (ServicePublisher) bundleContext.getService(serviceReference);
		System.out.println(servicePublish.ServicePublisher());
		
		HashMap<Integer,Employee> employee = new HashMap<>();
		String action;
		int id;
		
		Scanner str1 = new Scanner(System.in);
		Scanner str2 = new Scanner(System.in);
		
		
		System.out.println("-----------------------------------------");
		System.out.println("| Welcome to Employee Managment System |");
		System.out.println("-----------------------------------------");
		
		System.out.println();
		
		
		System.out.println("a. To add an employee enter 'ADD'");
		System.out.println("b. To remove an employee enter 'REMOVE'");
		System.out.println("c. To view all employees enter 'VIEW'");
		System.out.println("d. To view an employee enter 'VIEW EMPLOYEE'");
		System.out.println("e. To exit enter 'EXIT'"+"\n");
		
		Scanner ob = new Scanner(System.in);
		
		//enter your choice
		System.out.print("Enter: ");
		action = ob.nextLine();
		
		System.out.println();
		
		while(!action.equalsIgnoreCase("exit")) {
			
			if(action.equalsIgnoreCase("add")) {
				
				System.out.print("Enter the id of the employee: ");
				id = ob.nextInt();
				
				if(servicePublish.contains(id)==true) {
					System.out.println("Employee already exists! "+"\n");
					
				}else {
					
					System.out.print("Enter the name of the employee: ");
					String name = str1.nextLine();
					System.out.print("Enter the contact number of the employee: ");
					String contact = str1.nextLine();
				    
				    while(contact.length()!=10) {
				    	System.out.print("Incorrect contact number.Please enter again: ");
					    contact = str1.nextLine();
				    }
					System.out.print("Enter the age of the employee: ");
					int age = str2.nextInt();
				    
				  
				    
					System.out.println();
				    
					servicePublish.addEmployees(id, name, contact, age);
					System.out.println("Employee is Added"+"\n");
					
				}
				
			}else if(action.equalsIgnoreCase("remove")) {
				System.out.print("Enter the id of the employee you want to remove: ");
				id = ob.nextInt();
				
				if(servicePublish.contains(id)==false) {
					System.out.println("Employee does not exist"+"\n");
					
				}else {
					
					if(servicePublish.removeEmployees(id)==true) {
						System.out.println("Employee is removed"+"\n");
					}
						
				}	
				
			}else if(action.equalsIgnoreCase("view")){
				servicePublish.viewEmployees();
				
			}else if(action.equalsIgnoreCase("view employee")) {
				System.out.print("Enter the id of the employee: ");
				id = ob.nextInt();
				servicePublish.getAnEmployee(id);
				
				
			}else if(!action.equalsIgnoreCase("view") || !action.equalsIgnoreCase("add") || !action.equalsIgnoreCase("remove") || !action.equalsIgnoreCase("exit")|| !action.equalsIgnoreCase("view employee")) {
				System.out.println("Please enter a valid input! ");
				
			}
			
			System.out.println("a. To add an employee enter 'ADD'");
			System.out.println("b. To remove an employee enter 'REMOVE'");
			System.out.println("c. To view all employees enter 'VIEW'");
			System.out.println("d. To view an employee enter 'VIEW EMPLOYEE'");
			System.out.println("e. To exit enter 'EXIT'"+"\n");
			
			ob = new Scanner(System.in);
			str1 = new Scanner(System.in);
			System.out.print("Enter: ");
			action = ob.nextLine();
				
		}
		
		System.out.println("-----------------------------------------");
		System.out.println("|             Thank you!!!              |");
		System.out.println("-----------------------------------------");
		
		
		
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("Good Bye!!!");
		bundleContext.ungetService(serviceReference);
	}

}
