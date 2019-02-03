package org.transitclock.db.structs;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.transitclock.gtfsride.structs.GtfsRideRidership;

@Entity(name = "Ridership")
@DynamicUpdate
@Table(name = "Ridership")
public class Ridership {

	public Ridership(GtfsRideRidership ridership) {
		// TODO Auto-generated constructor stub
	}

}
