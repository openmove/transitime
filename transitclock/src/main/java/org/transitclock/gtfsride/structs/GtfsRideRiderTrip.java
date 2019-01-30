package org.transitclock.gtfsride.structs;

import org.apache.commons.csv.CSVRecord;
import org.transitclock.utils.csv.CsvBase;

public class GtfsRideRiderTrip extends CsvBase {
	String riderId;
	String agencyId;
	String tripId;
	String boardingStopId;
	Integer boardingStopSequence;
	String alightingStopId;
	Integer alightingStopSequence;
	String serviceDate;
	String boardingTime;
	String alightingTime;
	Integer riderType;
	String riderTypeDescription;
	Double farePaid;
	Integer transactionType;
	Integer fareMedia;
	Integer accompanyingDevice;
	Boolean transferStatus;
	public GtfsRideRiderTrip(CSVRecord record, boolean supplementalFile, String fileName) {
		super(record, supplementalFile, fileName);
		this.riderId=this.getRequiredValue(record, "rider_id");
		this.agencyId=this.getOptionalValue(record, "agency_id");
		this.tripId=this.getOptionalValue(record, "trip_id");
		this.boardingStopId=this.getOptionalValue(record, "boarding_stop_id");
		this.boardingStopSequence=this.getOptionalIntegerValue(record, "boarding_stop_sequence");
		this.alightingStopId=this.getOptionalValue(record, "alighting_stop_id");
		this.alightingStopSequence=this.getOptionalIntegerValue(record, "alighting_stop_sequence");
		this.serviceDate=this.getOptionalValue(record, "service_date");
		this.boardingTime=this.getOptionalValue(record, "boarding_time");
		this.alightingTime=this.getOptionalValue(record, "alighting_time");
		this.riderType=this.getOptionalIntegerValue(record, "rider_type");
		this.riderTypeDescription=this.getOptionalValue(record, "rider_type_description");
		this.farePaid=this.getOptionalDoubleValue(record, "fare_paid");
		this.transactionType=this.getOptionalIntegerValue(record, "transaction_type");
		this.fareMedia=this.getOptionalIntegerValue(record, "fare_media");
		this.accompanyingDevice=this.getOptionalIntegerValue(record, "accompanying_device");
		this.transferStatus=this.getOptionalBooleanValue(record, "transfer_status");		
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
		return "GtfsRideRiderTrip [riderId=" + riderId + ", agencyId=" + agencyId + ", tripId=" + tripId
				+ ", boardingStopId=" + boardingStopId + ", boardingStopSequence=" + boardingStopSequence
				+ ", alightingStopId=" + alightingStopId + ", alightingStopSequence=" + alightingStopSequence
				+ ", serviceDate=" + serviceDate + ", boardingTime=" + boardingTime + ", alightingTime=" + alightingTime
				+ ", riderType=" + riderType + ", riderTypeDescription=" + riderTypeDescription + ", farePaid="
				+ farePaid + ", transactionType=" + transactionType + ", fareMedia=" + fareMedia
				+ ", accompanyingDevice=" + accompanyingDevice + ", transferStatus=" + transferStatus + "]";
	}


}
