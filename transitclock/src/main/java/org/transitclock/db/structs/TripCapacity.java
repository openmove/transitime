package org.transitclock.db.structs;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.transitclock.gtfsride.structs.GtfsRideTripCapacity;

@Entity(name = "TripCapacity")
@DynamicUpdate
@Table(name = "TripCapacity")
public class TripCapacity  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4412354020561073564L;
	@Id
	@Column
	String  agencyId;
	@Id
	@Column
	String tripId;
	@Id	
	@Column
	String serviceDate;
	@Column
	String vehicleDescription;
	@Column
	Integer seatedCapacity;
	@Column
	Integer standingCapacity;
	@Column
	Integer wheelchairCapacity;
	@Column
	Integer bikeCapacity;
	public TripCapacity() {
		super();
		this.agencyId=null;
		this.tripId=null;
		this.serviceDate=null;
		this.vehicleDescription=null;
		this.seatedCapacity=null;
		this.standingCapacity=null;
		this.wheelchairCapacity=null;
		this.bikeCapacity=null;
	}
	public TripCapacity(String agencyId, String tripId, String serviceDate, String vehicleDescription,
			Integer seatedCapacity, Integer standingCapacity, Integer wheelchairCapacity, Integer bikeCapacity) {
		super();
		this.agencyId = agencyId;
		this.tripId = tripId;
		this.serviceDate = serviceDate;
		this.vehicleDescription = vehicleDescription;
		this.seatedCapacity = seatedCapacity;
		this.standingCapacity = standingCapacity;
		this.wheelchairCapacity = wheelchairCapacity;
		this.bikeCapacity = bikeCapacity;
	}
	public TripCapacity(GtfsRideTripCapacity capacity) {
		this.agencyId=capacity.getAgencyId();
		this.tripId=capacity.getTripId();
		this.serviceDate=capacity.getServiceDate();
		this.vehicleDescription=capacity.getVehicleDescription();
		this.seatedCapacity=capacity.getSeatedCapacity();
		this.standingCapacity=capacity.getStandingCapacity();
		this.wheelchairCapacity=capacity.getWheelchairCapacity();
		this.bikeCapacity=capacity.getBikeCapacity();
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getAgencyId() {
		return agencyId;
	}
	public String getTripId() {
		return tripId;
	}
	public String getServiceDate() {
		return serviceDate;
	}
	public String getVehicleDescription() {
		return vehicleDescription;
	}
	public Integer getSeatedCapacity() {
		return seatedCapacity;
	}
	public Integer getStandingCapacity() {
		return standingCapacity;
	}
	public Integer getWheelchairCapacity() {
		return wheelchairCapacity;
	}
	public Integer getBikeCapacity() {
		return bikeCapacity;
	}
	@Override
	public String toString() {
		return "TripCapacity [agencyId=" + agencyId + ", tripId=" + tripId + ", serviceDate=" + serviceDate
				+ ", vehicleDescription=" + vehicleDescription + ", seatedCapacity=" + seatedCapacity
				+ ", standingCapacity=" + standingCapacity + ", wheelchairCapacity=" + wheelchairCapacity
				+ ", bikeCapacity=" + bikeCapacity + "]";
	}
	
}
