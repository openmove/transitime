package org.transitclock.gtfsride.readers;

import java.text.ParseException;

import org.apache.commons.csv.CSVRecord;
import org.transitclock.gtfsride.structs.GtfsRideBoardAlight;

import org.transitclock.utils.csv.CsvBaseReader;

public class GtfsRideBoardAlightReader extends CsvBaseReader<GtfsRideBoardAlight> {
	public GtfsRideBoardAlightReader(String dirName, String fileName, boolean required, boolean supplemental) {
		super(dirName, "board_alight.txt", true, false);

	}

	@Override
	protected GtfsRideBoardAlight handleRecord(CSVRecord record, boolean supplemental)
			throws ParseException, NumberFormatException {

		return new GtfsRideBoardAlight(record, supplemental, getFileName());
	}

}
