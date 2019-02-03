package org.transitclock.db.structs;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.transitclock.gtfsride.structs.GtfsRideRidership;

@Entity(name = "Ridership")
@DynamicUpdate
@Table(name = "Ridership")
public class Ridership implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8724393819941772565L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	long id;	
	@Column
	Integer totalBoardings;
	@Column
	Integer totalAlightings;
	@Column
	String ridershipStartDate;
	@Column
	String ridershipEndDate;
	@Column
	String ridershipStartTime;
	@Column
	String ridershipEndTime;
	@Column
	String serviceId;
	@Column
	Boolean monday;
	@Column
	Boolean tuesday;
	@Column
	Boolean wednesday;
	@Column
	Boolean thursday;
	@Column
	Boolean friday;
	@Column
	Boolean saturday;
	@Column
	Boolean sunday;
	@Column
	String agencyId;
	@Column
	String routeId;
	@Column
	String directionId;
	@Column
	String tripId;
	@Column
	String stopId;
	public Ridership() {
		totalBoardings=null;
		totalAlightings=null;
		ridershipStartDate=null;
		ridershipEndDate=null;
		ridershipStartTime=null;
		ridershipEndTime=null;
		serviceId=null;
		monday=null;
		tuesday=null;
		wednesday=null;
		thursday=null;
		friday=null;
		saturday=null;
		sunday=null;
		agencyId=null;
		routeId=null;
		directionId=null;
		tripId=null;
		stopId=null;
	}
	public Ridership(GtfsRideRidership ridership) {
		totalBoardings=ridership.getTotalBoardings();
		totalAlightings=ridership.getTotalAlightings();
		ridershipStartDate=ridership.getRidershipStartDate();
		ridershipEndDate=ridership.getRidershipEndDate();
		ridershipStartTime=ridership.getRidershipStartTime();
		ridershipEndTime=ridership.getRidershipEndTime();
		serviceId=ridership.getServiceId();
		monday=ridership.getMonday();
		tuesday=ridership.getTuesday();
		wednesday=ridership.getWednesday();
		thursday=ridership.getThursday();
		friday=ridership.getFriday();
		saturday=ridership.getSaturday();
		sunday=ridership.getSunday();
		agencyId=ridership.getAgencyId();
		routeId=ridership.getRouteId();
		directionId=ridership.getDirectionId();
		tripId=ridership.getTripId();
		stopId=ridership.getStopId();
	}
	public Integer getTotalBoardings() {
		return totalBoardings;
	}
	public Integer getTotalAlightings() {
		return totalAlightings;
	}
	public String getRidershipStartDate() {
		return ridershipStartDate;
	}
	public String getRidershipEndDate() {
		return ridershipEndDate;
	}
	public String getRidershipStartTime() {
		return ridershipStartTime;
	}
	public String getRidershipEndTime() {
		return ridershipEndTime;
	}
	public String getServiceId() {
		return serviceId;
	}
	public Boolean getMonday() {
		return monday;
	}
	public Boolean getTuesday() {
		return tuesday;
	}
	public Boolean getWednesday() {
		return wednesday;
	}
	public Boolean getThursday() {
		return thursday;
	}
	public Boolean getFriday() {
		return friday;
	}
	public Boolean getSaturday() {
		return saturday;
	}
	public Boolean getSunday() {
		return sunday;
	}
	public String getAgencyId() {
		return agencyId;
	}
	public String getRouteId() {
		return routeId;
	}
	public String getDirectionId() {
		return directionId;
	}
	public String getTripId() {
		return tripId;
	}
	public String getStopId() {
		return stopId;
	}
	@Override
	public String toString() {
		return "Ridership [totalBoardings=" + totalBoardings + ", totalAlightings=" + totalAlightings
				+ ", ridershipStartDate=" + ridershipStartDate + ", ridershipEndDate=" + ridershipEndDate
				+ ", ridershipStartTime=" + ridershipStartTime + ", ridershipEndTime=" + ridershipEndTime
				+ ", serviceId=" + serviceId + ", monday=" + monday + ", tuesday=" + tuesday + ", wednesday="
				+ wednesday + ", thursday=" + thursday + ", friday=" + friday + ", saturday=" + saturday + ", sunday="
				+ sunday + ", agencyId=" + agencyId + ", routeId=" + routeId + ", directionId=" + directionId
				+ ", tripId=" + tripId + ", stopId=" + stopId + "]";
	}

}
