package org.transitclock.core.predictiongenerator.datafilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.transitclock.config.IntegerConfigValue;
import org.transitclock.config.LongConfigValue;
import org.transitclock.db.structs.PredictionEvent;
import org.transitclock.ipc.data.IpcArrivalDeparture;
import org.transitclock.monitoring.MonitoringService;
import org.transitclock.utils.Time;
/**
 * 
 * @author scrudden
 * Filter by
 * schedule adherence min and max
 * travel time min and max
 */

public class TravelTimeDataFilterImpl implements TravelTimeDataFilter {

	private static LongConfigValue maxTravelTimeAllowedInModel = new LongConfigValue(
			"transitclock.prediction.travel.maxTravelTimeAllowedInModel", (long) (20 * Time.MS_PER_MIN),
			"Max travel time to be considered in algorithm. Milliseconds.");
	private static LongConfigValue minTravelTimeAllowedInModel = new LongConfigValue(
			"transitclock.prediction.travel.minTravelTimeAllowedInModel", (long) 1000,
			"Min travel time to be considered in algorithm. Milliseconds.");
	private static IntegerConfigValue minSceheduleAdherence = new IntegerConfigValue(
			"transitclock.prediction.travel.minSceheduleAdherence", (int) (10 * Time.SEC_PER_MIN),
			"If schedule adherence of vehicle is outside this then not considerd in travel time algorithm.");
	private static IntegerConfigValue maxSceheduleAdherence = new IntegerConfigValue(
			"transitclock.prediction.travel.maxSceheduleAdherence", (int) (10 * Time.SEC_PER_MIN),
			"If schedule adherence of vehicle is outside this then not considerd in travel time algorithm.");
	
	private static final Logger logger = LoggerFactory.getLogger(DwellTimeDataFilterImpl.class);
	
	private static MonitoringService monitoring=null;
	
	@Override
	public boolean filter(IpcArrivalDeparture departure, IpcArrivalDeparture arrival) {
		if (arrival != null && departure != null) {
			long traveltime = arrival .getTime().getTime() - departure.getTime().getTime();
			
			if(departure.getScheduledAdherence() != null && arrival.getScheduledAdherence() != null)
			{
				getMonitoring().rateMetric("ScheduleAdherenceBothSet", true);
			}else
			{
				getMonitoring().rateMetric("ScheduleAdherenceBothSet", false);
			}
			if(departure.getScheduledAdherence() == null)
			{
				getMonitoring().rateMetric("ScheduleAdherenceDepartureSet", false);
			}else
			{
				getMonitoring().rateMetric("ScheduleAdherenceDepartureSet", true);
			}
			if(arrival.getScheduledAdherence() == null)
			{
				getMonitoring().rateMetric("ScheduleAdherenceArrivalSet", false);
			}else
			{
				getMonitoring().rateMetric("ScheduleAdherenceArrivalSet", true);
			}				
			if (departure.getScheduledAdherence() == null 
					|| (departure.getScheduledAdherence() != null && departure.getScheduledAdherence()
					.isWithinBounds(minSceheduleAdherence.getValue(), maxSceheduleAdherence.getValue()))) {	
				// TODO Arrival schedule adherence appears not to be set much. So
				// only stop if set and outside range.
				if (arrival.getScheduledAdherence() == null || arrival.getScheduledAdherence()
						.isWithinBounds(minSceheduleAdherence.getValue(), maxSceheduleAdherence.getValue())) {
					if (traveltime < maxTravelTimeAllowedInModel.getValue()
							&& traveltime > minTravelTimeAllowedInModel.getValue()) {
						
						getMonitoring().rateMetric("TravelTimeDataFilterFilteredTravelTime", false);
						return false;
												
					} else {
						logger.warn("Travel time {} outside allowable range for {} to {}.", traveltime, departure, arrival);
					}
				} else {
					logger.warn("Schedule adherence outside allowable range. " + arrival);
				}
			} else {
				logger.warn("Schedule adherence outside allowable range. " + departure);
			}
		} else {
			logger.warn("Arrival and/or departure not set.");
		}
		getMonitoring().rateMetric("TravelTimeDataFilterFilteredTravelTime", true);
		return true;
	}

	/**
	 * lazy load Monitoring service.
	 * @return
	 */
	protected MonitoringService getMonitoring() {
		if (monitoring == null)
			monitoring = MonitoringService.getInstance();
		return monitoring;
	}
}
