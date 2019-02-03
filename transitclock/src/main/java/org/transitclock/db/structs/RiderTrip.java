package org.transitclock.db.structs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.transitclock.gtfsride.structs.GtfsRideRiderTrip;

@Entity(name = "RiderTrip")
@DynamicUpdate
@Table(name = "RiderTrip")
public class RiderTrip {
	@Id	
	@Column
	String riderId;
	@Id
	@Column
	String agencyId;
	@Column
	String tripId;	
	@Id
	@Column
	String boardingStopId;
	@Column
	Integer boardingStopSequence;
	@Column
	String alightingStopId;
	@Column
	Integer alightingStopSequence;
	@Id
	@Column
	String serviceDate;
	@Id
	@Column
	String boardingTime;
	@Column
	String alightingTime;
	@Column
	Integer riderType;
	@Column
	String riderTypeDescription;
	@Column
	Double farePaid;
	@Column
	Integer transactionType;
	@Column
	Integer fareMedia;
	@Column
	Integer accompanyingDevice;
	@Column
	Boolean transferStatus;
	public RiderTrip()
	{
		this.agencyId=null;
		this.riderId=null;
		this.tripId=null;
		this.boardingStopId=null;;
		this.boardingStopSequence=null;
		this.alightingStopId=null;
		this.alightingStopSequence=null;
		this.serviceDate=null;
		this.boardingTime=null;
		this.alightingTime=null;
		this.riderType=null;
		this.riderTypeDescription=null;
		this.farePaid=null;
		this.transactionType=null;
		this.fareMedia=null;
		this.accompanyingDevice=null;
		this.transferStatus=null;
		
	}
	public RiderTrip(GtfsRideRiderTrip trip) {
		this.agencyId=trip.getAgencyId();
		this.riderId=trip.getRiderId();
		this.tripId=trip.getTripId();
		this.boardingStopId=trip.getBoardingStopId();
		this.boardingStopSequence=trip.getBoardingStopSequence();
		this.alightingStopId=trip.getAlightingStopId();
		this.alightingStopSequence=trip.getAlightingStopSequence();
		this.serviceDate=trip.getServiceDate();
		this.boardingTime=trip.getBoardingTime();
		this.alightingTime=trip.getAlightingTime();
		this.riderType=trip.getRiderType();
		this.riderTypeDescription=trip.getRiderTypeDescription();
		this.farePaid=trip.getFarePaid();
		this.transactionType=trip.getTransactionType();
		this.fareMedia=trip.getFareMedia();
		this.accompanyingDevice=trip.getAccompanyingDevice();
		this.transferStatus=trip.getTransferStatus();
		
	}
	public String getRiderId() {
		return riderId;
	}
	public String getAgencyId() {
		return agencyId;
	}
	public String getTripId() {
		return tripId;
	}
	public String getBoardingStopId() {
		return boardingStopId;
	}
	public Integer getBoardingStopSequence() {
		return boardingStopSequence;
	}
	public String getAlightingStopId() {
		return alightingStopId;
	}
	public Integer getAlightingStopSequence() {
		return alightingStopSequence;
	}
	public String getServiceDate() {
		return serviceDate;
	}
	public String getBoardingTime() {
		return boardingTime;
	}
	public String getAlightingTime() {
		return alightingTime;
	}
	public Integer getRiderType() {
		return riderType;
	}
	public String getRiderTypeDescription() {
		return riderTypeDescription;
	}
	public Double getFarePaid() {
		return farePaid;
	}
	public Integer getTransactionType() {
		return transactionType;
	}
	public Integer getFareMedia() {
		return fareMedia;
	}
	public Integer getAccompanyingDevice() {
		return accompanyingDevice;
	}
	public Boolean getTransferStatus() {
		return transferStatus;
	}
	@Override
	public String toString() {
		return "RiderTrip [riderId=" + riderId + ", agencyId=" + agencyId + ", tripId=" + tripId + ", boardingStopId="
				+ boardingStopId + ", boardingStopSequence=" + boardingStopSequence + ", alightingStopId="
				+ alightingStopId + ", alightingStopSequence=" + alightingStopSequence + ", serviceDate=" + serviceDate
				+ ", boardingTime=" + boardingTime + ", alightingTime=" + alightingTime + ", riderType=" + riderType
				+ ", riderTypeDescription=" + riderTypeDescription + ", farePaid=" + farePaid + ", transactionType="
				+ transactionType + ", fareMedia=" + fareMedia + ", accompanyingDevice=" + accompanyingDevice
				+ ", transferStatus=" + transferStatus + "]";
	}
	
}
