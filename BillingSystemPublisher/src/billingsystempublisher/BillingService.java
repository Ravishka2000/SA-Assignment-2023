package billingsystempublisher;

public interface BillingService {
	
	public void addPrice(String name, int quantity);
	public void removePrice(String name, int quantity);
	public double getTotal();
	public void getTotalBill();
}
