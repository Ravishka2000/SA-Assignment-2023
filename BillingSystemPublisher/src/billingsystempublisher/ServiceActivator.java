package billingsystempublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	ServiceRegistration<?> serviceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Billing Service Started......");
		BillingService inventory = new BillingServiceImpl();
		
		serviceRegistration = context.registerService(BillingService.class.getName(), inventory, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Billing Service Stopped.....");
		serviceRegistration.unregister();
	}

}
