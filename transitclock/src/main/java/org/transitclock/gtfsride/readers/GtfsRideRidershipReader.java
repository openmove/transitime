package org.transitclock.gtfsride.readers;

import java.text.ParseException;

import org.apache.commons.csv.CSVRecord;
import org.transitclock.gtfsride.structs.GtfsRideRidership;
import org.transitclock.utils.csv.CsvBaseReader;

public class GtfsRideRidershipReader extends CsvBaseReader<GtfsRideRidership> {

	protected GtfsRideRidershipReader(String dirName, String fileName, boolean required, boolean supplemental) {
		
		super(dirName, "ridership.txt", true, false);
	}

	@Override
	protected GtfsRideRidership handleRecord(CSVRecord record, boolean supplemental)
			throws ParseException, NumberFormatException {
	
		return new GtfsRideRidership(record, supplemental, getFileName());
	}

	

}
