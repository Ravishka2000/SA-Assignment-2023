package employeepublisher;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	
	ServiceRegistration servicePublishRegistration;
	

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Publisher Service Started");
		ServicePublisher servicepublisher = new ServicePublisherImpl();
		servicePublishRegistration = bundleContext.registerService(ServicePublisher.class.getName(), servicepublisher, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("Publisher Stop");
		servicePublishRegistration.unregister();
		
	}

}
