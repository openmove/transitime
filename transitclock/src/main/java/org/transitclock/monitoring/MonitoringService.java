package org.transitclock.monitoring;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class MonitoringService {

	private MeterRegistry registry = null;

	private static MonitoringService singleton = null;

	private MonitoringService() {
		registry = new SimpleMeterRegistry();
	}

	public synchronized static MonitoringService getInstance() {
		if (singleton == null)
			singleton = new MonitoringService();
		return singleton;
	}
	public MeterRegistry getRegistry()
	{
		return registry;		
	}
}
