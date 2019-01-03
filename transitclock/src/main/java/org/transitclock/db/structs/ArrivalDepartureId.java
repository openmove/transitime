package org.transitclock.db.structs;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.transitclock.db.hibernate.HibernateUtils;

public class ArrivalDepartureId implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1172014991513474157L;

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getStopId() {
		return stopId;
	}

	public void setStopId(String stopId) {
		this.stopId = stopId;
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public Date getTime() {
		return time;
	}

	public int getGtfsStopSeq() {
		return gtfsStopSeq;
	}

	public boolean isArrival() {
		return isArrival;
	}


	@Column(length = HibernateUtils.DEFAULT_ID_SIZE)
	private String vehicleId;

	// Originally did not use msec precision (datetime(3)) specification
	// because arrival/departure times are only estimates and having such
	// precision is not generally appropriate. But found that then some
	// arrival and departures for a stop would have the same time and when
	// one would query for the arrivals/departures and order by time one
	// could get a departure before an arrival. To avoid this kind of
	// incorrect ordering using the additional precision. And this way
	// don't have to add an entire second to a departure time to make
	// sure that it is after the arrival. Adding a second is an
	// exaggeration because it implies the vehicle was stopped for a second
	// when most likely it zoomed by the stop. It looks better to add
	// only a msec to make the departure after the arrival.

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private final Date time = null;


	@Column(length = HibernateUtils.DEFAULT_ID_SIZE)
	private String stopId;

	// From the GTFS stop_times.txt file for the trip. The gtfsStopSeq can
	// be different from stopPathIndex. The stopIndex is included here so that
	// it is easy to find the corresponding stop in the stop_times.txt file.
	// It needs to be part of the @Id because can have loops for a route
	// such that a stop is served twice on a trip. Otherwise would get a
	// constraint violation.

	@Column
	private final Integer gtfsStopSeq = null;

	public ArrivalDepartureId() {
		super();		
	}


	@Id
	@Column
	private final Boolean isArrival = null;

	@Id
	@Column(length = HibernateUtils.DEFAULT_ID_SIZE)
	private String tripId;
}
