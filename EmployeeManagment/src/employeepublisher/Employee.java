package employeepublisher;

public class Employee {
	
	private int id;
	private String name;
	private String contact;
	private int age;
	
	
	public Employee(int id, String name, String contact, int age) {
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.age = age;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}


	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}


	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}


	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
	

}
