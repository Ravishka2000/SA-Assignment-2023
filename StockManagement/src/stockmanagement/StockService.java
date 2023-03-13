package stockmanagement;

public interface StockService {
	public void addProduct(String name, int quantity, int reorderLevel, double unitPrice);
	public void dispatchProduct(String name, int quantity);
	public int getQuantity(String name);
	public boolean deleteProduct(String name);
}
