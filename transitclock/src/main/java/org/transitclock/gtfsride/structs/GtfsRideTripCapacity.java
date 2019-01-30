package org.transitclock.gtfsride.structs;

import org.apache.commons.csv.CSVRecord;
import org.transitclock.utils.csv.CsvBase;

public class GtfsRideTripCapacity extends CsvBase {

	String  agencyId;
	String tripId;
	String serviceDate;
	String vehicleDescription;
	Integer seatedCapacity;
	Integer standingCapacity;
	Integer wheelchairCapacity;
	Integer bikeCapacity;
	
	public GtfsRideTripCapacity(CSVRecord record, boolean supplementalFile, String fileName) {
		super(record, supplementalFile, fileName);
		agencyId=this.getOptionalValue(record, "agency_id");
		tripId=this.getOptionalValue(record,"trip_id");
		serviceDate=this.getOptionalValue(record, "service_date");
		vehicleDescription=this.getOptionalValue(record, "vehicle_description");
		seatedCapacity=this.getOptionalIntegerValue(record, "seated_capacity");
		wheelchairCapacity=this.getOptionalIntegerValue(record, "wheelchair_capacity");
		bikeCapacity=this.getOptionalIntegerValue(record, "bike_capacity");
	}

	public String getAgencyId() {
		return agencyId;
	}

	public String getTripId() {
		return tripId;
	}

	public String getServiceDate() {
		return serviceDate;
	}

	public String getVehicleDescription() {
		return vehicleDescription;
	}

	public Integer getSeatedCapacity() {
		return seatedCapacity;
	}

	public Integer getStandingCapacity() {
		return standingCapacity;
	}

	public Integer getWheelchairCapacity() {
		return wheelchairCapacity;
	}

	public Integer getBikeCapacity() {
		return bikeCapacity;
	}


}
