package employeepublisher;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServicePublisherImpl implements ServicePublisher {
	
	HashMap<Integer,Employee> employee = new HashMap<>();

	@Override
	public String ServicePublisher() {
		return "Execute the publish service of ServicePublish";
	}

	@Override
	public void viewEmployees() {
		
		System.out.printf("| %-18s | %-18s | %-18s |%-18s |%n", "Employee ID", "Employee Name", "Contact Number","Age" );
		
		System.out.println("----------------------------------------------------------------------------------------");
		
		for(Map.Entry<Integer, Employee> entry:employee.entrySet()) {
			System.out.printf("| %-18s | %-18s | %-18s |%-18s |%n", entry.getValue().getId(), entry.getValue().getName(),  entry.getValue().getContact(),entry.getValue().getAge());	
		
		System.out.println("----------------------------------------------------------------------------------------");
		
		
			
			
		}
		System.out.println();
		
	}

	@Override
	public void addEmployees(int id, String name, String contact, int age) {
		Employee emp = new Employee(id, name,  contact,  age);
		employee.put(id, emp);
	}

	@Override
	public boolean removeEmployees(int id) {
		if(!employee.containsKey(id)) {
			return false;
			
		}else {
			employee.remove(id);
			return true;
				
		}	
		
		
	}

	@Override
	public boolean contains(int id) {
		if(employee.containsKey(id)) {
			return true;
		}
		return false;
	}

	@Override
	public void getAnEmployee(int id) {
		
		if(!employee.containsKey(id)) {
			System.out.println("Employee does not exist");
			
		}else {
			

			System.out.printf("| %-18s | %-18s | %-18s |%-18s |%n", "Employee ID", "Employee Name", "Contact Number","Age" );
			
			System.out.println("----------------------------------------------------------------------------------------");
			
			for(Map.Entry<Integer, Employee> entry:employee.entrySet()) {
				if(entry.getKey()==id) {
					System.out.printf("| %-18s | %-18s | %-18s |%-18s |%n", entry.getValue().getId(), entry.getValue().getName(),  entry.getValue().getContact(),entry.getValue().getAge());	
				}
				
				
			}
			System.out.println();
			
			
		}
		
		
	}

	@Override
	public void importToCsv(String path) {
		// TODO Auto-generated method stub
		String csvHeader = "Employee ID,Employee Name,Contact Number,Age,\n";
        String csvRowFormat = "%d,%s,%s,%d\n";
        
        try {
            FileWriter writer = new FileWriter(path);
            writer.append(csvHeader);

            for (Integer key : employee.keySet()) {
            	
                String name = employee.get(key).getName();
                String contact = employee.get(key).getContact();
                int age = employee.get(key).getAge();
                
                String row = String.format(csvRowFormat, key, name,contact,age);
                writer.append(row);
            }
            
            System.out.println("Employee list is exported to "+path);

            writer.flush();
            writer.close();
        } catch (IOException e) {
        	System.out.println("Can not find the file path");
            e.printStackTrace();
        }
		
	}
	
	

}
