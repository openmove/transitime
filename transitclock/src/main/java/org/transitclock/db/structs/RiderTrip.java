package org.transitclock.db.structs;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.transitclock.gtfsride.structs.GtfsRideRiderTrip;

@Entity(name = "RiderTrip")
@DynamicUpdate
@Table(name = "RiderTrip")
public class RiderTrip {

	public RiderTrip(GtfsRideRiderTrip trip) {
		
	}

}
