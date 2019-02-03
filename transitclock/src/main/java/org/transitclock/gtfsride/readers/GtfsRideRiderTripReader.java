package org.transitclock.gtfsride.readers;

import java.text.ParseException;

import org.apache.commons.csv.CSVRecord;
import org.transitclock.gtfsride.structs.GtfsRideRiderTrip;
import org.transitclock.utils.csv.CsvBaseReader;

public class GtfsRideRiderTripReader extends CsvBaseReader<GtfsRideRiderTrip>{
	
	public GtfsRideRiderTripReader(String dirName, String fileName, boolean required, boolean supplemental) {
		super(dirName, "rider_trip.txt", true, false);
	}

	@Override
	protected GtfsRideRiderTrip handleRecord(CSVRecord record, boolean supplemental)
			throws ParseException, NumberFormatException {		
		return new GtfsRideRiderTrip(record, supplemental, getFileName());
	}
}