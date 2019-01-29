package org.transitclock.gtfsride.readers;

import java.text.ParseException;

import org.apache.commons.csv.CSVRecord;
import org.transitclock.gtfsride.structs.GtfsRideRideFeedInfo;
import org.transitclock.utils.csv.CsvBaseReader;

public class GtfsRideRideFeedInfoReader extends CsvBaseReader<GtfsRideRideFeedInfo>{
	
	protected GtfsRideRideFeedInfoReader(String dirName) {
		super(dirName, "ride_feed_info.txt", true, false);
	}

	@Override
	protected GtfsRideRideFeedInfo handleRecord(CSVRecord record, boolean supplemental)
			throws ParseException, NumberFormatException {		
		return new GtfsRideRideFeedInfo(record, supplemental, getFileName());
	}
}
