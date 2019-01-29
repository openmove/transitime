package org.transitclock.gtfsride.structs;

import org.apache.commons.csv.CSVRecord;
import org.transitclock.utils.csv.CsvBase;

public class GtfsRideRidership  extends CsvBase {

	private final Integer totalBoardings;
	private final Integer totalAlightings;
	private final String ridershipStartDate;
	private final String ridershipEndDate;
	private final String ridershipStartTime;
	private final String ridershipEndTime;
	private final String serviceId;
	private final Boolean monday;
	private final Boolean tuesday;
	private final Boolean wednesday;
	private final Boolean thursday;
	private final Boolean friday;
	private final Boolean saturday;
	private final Boolean sunday;
	private final String agencyId;
	private final String routeId;
	private final String directionId;
	private final String tripId;
	private final String stopId;
	
	
	
	public GtfsRideRidership(CSVRecord record, boolean supplementalFile, String fileName) {
		super(record, supplementalFile, fileName);
		this.totalBoardings=Integer.parseInt(this.getRequiredValue(record, "total_boardings"));
		this.totalAlightings=Integer.parseInt(this.getRequiredValue(record, "total_alightings"));
		this.ridershipStartDate=this.getRequiredValue(record, "ridership_start_date");
		this.ridershipEndDate=this.getRequiredValue(record, "ridership_end_date");
		this.ridershipStartTime=this.getRequiredValue(record, "ridership_start_time");
		this.ridershipEndTime=this.getRequiredValue(record, "ridership_end_time");
		this.serviceId=this.getOptionalValue(record, "serviceid");
		this.monday=Boolean.parseBoolean(this.getOptionalValue(record, "monday"));
		this.tuesday=Boolean.parseBoolean(this.getOptionalValue(record, "tuesday"));
		this.wednesday=Boolean.parseBoolean(this.getOptionalValue(record, "wednesday"));
		this.thursday=Boolean.parseBoolean(this.getOptionalValue(record, "thursday"));
		this.friday=Boolean.parseBoolean(this.getOptionalValue(record, "friday"));
		this.saturday=Boolean.parseBoolean(this.getOptionalValue(record, "saturday"));
		this.sunday=Boolean.parseBoolean(this.getOptionalValue(record, "sunday"));
		this.agencyId=this.getOptionalValue(record, "agency_id");
		this.routeId=this.getOptionalValue(record, "route_id");
		this.directionId=this.getOptionalValue(record, "direction_id");
		this.tripId=this.getOptionalValue(record, "trip_id");
		this.stopId=this.getOptionalValue(record, "stop_id");
	
	}
	public Integer getTotalBoardings() {
		return totalBoardings;
	}
	public Integer getTotalAlightings() {
		return totalAlightings;
	}
	public String getRidershipStartDate() {
		return ridershipStartDate;
	}
	public String getRidershipEndDate() {
		return ridershipEndDate;
	}
	public String getRidershipStartTime() {
		return ridershipStartTime;
	}
	public String getRidershipEndTime() {
		return ridershipEndTime;
	}
	public String getServiceId() {
		return serviceId;
	}
	public Boolean getMonday() {
		return monday;
	}
	public Boolean getTuesday() {
		return tuesday;
	}
	public Boolean getWednesday() {
		return wednesday;
	}
	public Boolean getThursday() {
		return thursday;
	}
	public Boolean getFriday() {
		return friday;
	}
	public Boolean getSaturday() {
		return saturday;
	}
	public Boolean getSunday() {
		return sunday;
	}
	public String getAgencyId() {
		return agencyId;
	}
	public String getRouteId() {
		return routeId;
	}
	public String getDirectionId() {
		return directionId;
	}
	public String getTripId() {
		return tripId;
	}
	public String getStopId() {
		return stopId;
	}
	@Override
	public String toString() {
		return "GtfsRideRidership [totalBoardings=" + totalBoardings + ", totalAlightings=" + totalAlightings
				+ ", ridershipStartDate=" + ridershipStartDate + ", ridershipEndDate=" + ridershipEndDate
				+ ", ridershipStartTime=" + ridershipStartTime + ", ridershipEndTime=" + ridershipEndTime
				+ ", serviceId=" + serviceId + ", monday=" + monday + ", tuesday=" + tuesday + ", wednesday="
				+ wednesday + ", thursday=" + thursday + ", friday=" + friday + ", saturday=" + saturday + ", sunday="
				+ sunday + ", agencyId=" + agencyId + ", routeId=" + routeId + ", directionId=" + directionId
				+ ", tripId=" + tripId + ", stopId=" + stopId + "]";
	}


}
