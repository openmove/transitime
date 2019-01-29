package org.transitclock.gtfsride;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.transitclock.db.hibernate.HibernateUtils;
import org.transitclock.gtfsride.GtfsRideDbWriter;
import org.transitclock.gtfs.GtfsData;
import org.transitclock.gtfsride.readers.GtfsRideBoardAlightReader;
import org.transitclock.gtfsride.structs.GtfsRideBoardAlight;

public class GTFSRideData {

	private final String gtfsRideDirectoryName;
	private final String agencyId;
	private final int configRev;
	private final boolean cleanupRevs;

	Session session;

	private List<GtfsRideBoardAlight> boardsAlights;
	private GtfsData gtfsData;

	// Logging
	public static final Logger logger = LoggerFactory.getLogger(GTFSRideData.class);

	/**
	 * Does all the work. Processes the data and store it in internal structures
	 */
	public void processData() {
		this.processBoardAlights();
		GtfsRideDbWriter dbWriter = new GtfsRideDbWriter(gtfsData, this);
		dbWriter.actuallyWriteData(session, configRev, cleanupRevs);
	}

	public GTFSRideData(GtfsData gtfsData, String gtfsRideDirectoryName, String agencyId, int configRev,
			boolean cleanupRevs) {
		super();
		this.gtfsRideDirectoryName = gtfsRideDirectoryName;
		this.agencyId = agencyId;
		this.gtfsData = gtfsData;
		this.configRev = configRev;
		this.cleanupRevs = cleanupRevs;

		SessionFactory sessionFactory = HibernateUtils.getSessionFactory(getAgencyId());
		session = sessionFactory.openSession();

	}

	void processBoardAlights() {
		GtfsRideBoardAlightReader reader = new GtfsRideBoardAlightReader(gtfsRideDirectoryName, null, false, false);
		boardsAlights = reader.get();
	}

	public String getGtfsRideDirectoryName() {
		return gtfsRideDirectoryName;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public List<GtfsRideBoardAlight> getBoardsAlights() {
		return boardsAlights;
	}

}
