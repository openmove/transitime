package org.transitclock.gtfsride;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.transitclock.db.structs.BoardAlight;
import org.transitclock.db.structs.RiderTrip;
import org.transitclock.db.structs.Ridership;
import org.transitclock.db.structs.TripCapacity;
import org.transitclock.gtfs.DbWriter;
import org.transitclock.gtfs.GtfsData;
import org.transitclock.gtfsride.structs.GtfsRideBoardAlight;
import org.transitclock.gtfsride.structs.GtfsRideRideFeedInfo;
import org.transitclock.gtfsride.structs.GtfsRideRiderTrip;
import org.transitclock.gtfsride.structs.GtfsRideRidership;
import org.transitclock.gtfsride.structs.GtfsRideTripCapacity;

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

		logger.info("Saving boards and alights to database.");				
		for(GtfsRideBoardAlight boardalight:gtfsRideData.getBoardsAlights())
		{						 							
			try {
				this.writeObject(session, new BoardAlight(boardalight));
			} catch (ParseException e) {
				logger.error("Failed to write "+boardalight.toString() + " to database. Exception : {}",e);
			}			
		}
		logger.info("Saving trip capacity to database.");
		for(GtfsRideTripCapacity capacity:gtfsRideData.getTripCapacity())
		{
			this.writeObject(session, new TripCapacity(capacity));
		}
		logger.info("Saving GTFS-ride feed info to database.");
		for(GtfsRideRideFeedInfo info:this.gtfsRideData.getInfo())
		{
			// TODO Save GTFS-ride feed info to database.
		}		
		logger.info("Saving ridership to database.");
		for(GtfsRideRidership ridership:this.gtfsRideData.getRidership())
		{
			this.writeObject(session, new Ridership(ridership));
		}
		
		logger.info("Saving rider trips to database.");
		for( GtfsRideRiderTrip trip:this.gtfsRideData.getRiderTrip())
		{
			this.writeObject(session, new RiderTrip(trip));
		}
		//super.actuallyWriteData(session, configRev, cleanupRevs);
		
	}

}
