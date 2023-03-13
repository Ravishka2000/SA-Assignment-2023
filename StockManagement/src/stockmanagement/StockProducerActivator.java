package stockmanagement;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class StockProducerActivator implements BundleActivator {

	ServiceRegistration<?> serviceRegistration;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Inventory Service Started......");
		StockService inventory = new StockServiceImpl();
		
		serviceRegistration = context.registerService(StockService.class.getName(), inventory, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Inventory Service Stopped.....");
		serviceRegistration.unregister();
	}

}
