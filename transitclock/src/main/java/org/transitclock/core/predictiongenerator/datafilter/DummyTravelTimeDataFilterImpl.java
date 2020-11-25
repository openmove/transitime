package org.transitclock.core.predictiongenerator.datafilter;

import org.transitclock.ipc.data.IpcArrivalDeparture;

public class DummyTravelTimeDataFilterImpl extends TravelTimeDataFilterImpl {
	
	@Override
	public boolean filter(IpcArrivalDeparture departure, IpcArrivalDeparture arrival) {
		getMonitoring().rateMetric("FilteredTravelTime", false);
		return false;
	}

}
