package org.transitclock.gtfsride;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.transitclock.gtfs.DbWriter;
import org.transitclock.gtfs.GtfsData;
import org.transitclock.gtfsride.structs.GtfsRideBoardAlight;

public class GtfsRideDbWriter extends DbWriter {

	private static final Logger logger = LoggerFactory
			.getLogger(GtfsRideDbWriter.class);
	private GTFSRideData gtfsRideData;
	
	
	public GtfsRideDbWriter(GtfsData gtfsData, GTFSRideData gtfsRideData) {
		super(gtfsData);
		this.gtfsRideData=gtfsRideData;		
	}

	@Override
	protected void actuallyWriteData(Session session, int configRev, boolean cleanupRevs) {

		logger.info("Saving boards and alights to database...");				
		for(GtfsRideBoardAlight boardalight:gtfsRideData.getBoardsAlights())
		{
			this.writeObject(session, boardalight);
		}
		super.actuallyWriteData(session, configRev, cleanupRevs);
		
	}

}
