package garageservice_publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	ServiceRegistration<?> serviceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Parking Service Started......");
		GarageServicePublish garage = new GarageServicePublishImpl();
		
		serviceRegistration = context.registerService(GarageServicePublish.class.getName(), garage, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Parking Service Stopped.....");
		serviceRegistration.unregister();
	}

}
