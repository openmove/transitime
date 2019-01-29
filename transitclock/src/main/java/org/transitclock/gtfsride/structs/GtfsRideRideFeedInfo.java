package org.transitclock.gtfsride.structs;

import org.apache.commons.csv.CSVRecord;
import org.transitclock.utils.csv.CsvBase;

public class GtfsRideRideFeedInfo  extends CsvBase {
	private final String rideFiles;
	private final String rideStartDate;
	private final String rideEndDate;
	private final String gtfsFeedDate;
	private final String rideFeedVersion;
	
	public GtfsRideRideFeedInfo(CSVRecord record, boolean supplemental, String fileName) {
		super(record, supplemental, fileName);
		rideFiles = this.getRequiredValue(record, "ride_files");
		rideStartDate= this.getOptionalValue(record, "ride_start_date");
		rideEndDate= this.getOptionalValue(record, "ride_end_date");
		gtfsFeedDate= this.getOptionalValue(record, "gtfs_feed_date");
		rideFeedVersion= this.getOptionalValue(record, "ride_feed_version");
						
	}

	public String getRideFiles() {
		return rideFiles;
	}

	public String getRideStartDate() {
		return rideStartDate;
	}

	public String getRideEndDate() {
		return rideEndDate;
	}

	public String getGtfsFeedDate() {
		return gtfsFeedDate;
	}

	public String getRideFeedVersion() {
		return rideFeedVersion;
	}

	@Override
	public String toString() {
		return "GtfsRideRideFeedInfo [rideFiles=" + rideFiles + ", rideStartDate=" + rideStartDate + ", rideEndDate="
				+ rideEndDate + ", gtfsFeedDate=" + gtfsFeedDate + ", rideFeedVersion=" + rideFeedVersion + "]";
	}	
		
}
