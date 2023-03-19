package employeepublisher;

public interface ServicePublisher {
	
	public String ServicePublisher();
	public void viewEmployees();
	public void addEmployees(int id, String name, String contact, int age);
	public boolean removeEmployees(int id);
	public boolean contains(int id);
	public void getAnEmployee(int id);
	public void importToCsv(String path);
	

}
