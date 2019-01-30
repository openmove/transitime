package org.transitclock.gtfsride.readers;

import java.text.ParseException;

import org.apache.commons.csv.CSVRecord;
import org.transitclock.gtfsride.structs.GtfsRideTripCapacity;
import org.transitclock.utils.csv.CsvBaseReader;

public class GtfsRideTripCapacityReader extends CsvBaseReader<GtfsRideTripCapacity>{
	
	public GtfsRideTripCapacityReader(String dirName, String fileName, boolean required, boolean supplemental) {
		super(dirName, "trip_capacity.txt", true, false);
	}

	@Override
	protected GtfsRideTripCapacity handleRecord(CSVRecord record, boolean supplemental)
			throws ParseException, NumberFormatException {		
		return new GtfsRideTripCapacity(record, supplemental, getFileName());
	}
}
