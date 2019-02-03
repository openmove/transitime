package org.transitclock.db.structs;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.transitclock.gtfsride.structs.GtfsRideBoardAlight;

@Entity(name = "BoardAlight")
@DynamicUpdate
@Table(name = "BoardAlight")
public class BoardAlight implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7454268288046748972L;
	@Column
	@Id
	String tripId;
	@Column
	@Id
	String stopId;

	@Id
	@Column
	Date serviceDate;

	@Column
	Integer stopSequence;
	@Column
	String recordUse;
	@Column
	Integer scheduleRelationship;
	@Column
	Integer boardings;
	@Column
	Integer alightings;
	@Column
	Integer currentLoad;
	@Column
	Integer loadCount;
	@Column
	Integer loadType;
	@Column
	Boolean rackDown;
	@Column
	Integer bikeBoardings;
	@Column
	Integer bikeAlightings;
	@Column
	Boolean rampUsed;
	@Column
	Integer rampBoardings;
	@Column
	Integer rampAlightings;

	@Column
	String serviceArrivalTime;
	@Column
	String serviceDepartureTime;
	@Column
	Integer source;

	public BoardAlight() {
		super();
		this.tripId = null;
		this.stopId = null;
		this.serviceDate = null;
		this.stopSequence = null;
		this.recordUse = null;
		this.scheduleRelationship = null;
		this.boardings = null;
		this.alightings = null;
		this.currentLoad = null;
		this.loadCount = null;
		this.loadType = null;
		this.rackDown = null;
		this.bikeBoardings = null;
		this.bikeAlightings = null;
		this.rampUsed = null;
		this.rampBoardings = null;
		this.rampAlightings = null;
		this.serviceArrivalTime = null;
		this.serviceDepartureTime = null;
		this.source = null;
	}

	public BoardAlight(String tripId, String stopId, Date serviceDate, Integer stopSequence, String recordUse,
			Integer scheduleRelationship, Integer boardings, Integer alightings, Integer currentLoad, Integer loadCount,
			Integer loadType, Boolean rackDown, Integer bikeBoardings, Integer bikeAlightings, Boolean rampUsed,
			Integer rampBoardings, Integer rampAlightings, String serviceArrivalTime, String serviceDepartureTime,
			Integer source) {
		super();
		this.tripId = tripId;
		this.stopId = stopId;
		this.serviceDate = serviceDate;
		this.stopSequence = stopSequence;
		this.recordUse = recordUse;
		this.scheduleRelationship = scheduleRelationship;
		this.boardings = boardings;
		this.alightings = alightings;
		this.currentLoad = currentLoad;
		this.loadCount = loadCount;
		this.loadType = loadType;
		this.rackDown = rackDown;
		this.bikeBoardings = bikeBoardings;
		this.bikeAlightings = bikeAlightings;
		this.rampUsed = rampUsed;
		this.rampBoardings = rampBoardings;
		this.rampAlightings = rampAlightings;
		this.serviceArrivalTime = serviceArrivalTime;
		this.serviceDepartureTime = serviceDepartureTime;
		this.source = source;
	}

	public BoardAlight(GtfsRideBoardAlight boardalight) throws ParseException {
		this.tripId = boardalight.getTripId();
		this.stopId = boardalight.getStopId();
		
		if(boardalight.getServiceDate()!=null)
		{
			DateFormat dateFormat = new SimpleDateFormat("YYYYMMDD");
			this.serviceDate = dateFormat.parse(boardalight.getServiceDate());
		}
		else
		{
			this.serviceDate = null;
		}
		this.stopSequence = boardalight.getStopSequence();
		this.recordUse = boardalight.getRecordUse();
		this.scheduleRelationship = boardalight.getScheduleRelationship();
		this.boardings = boardalight.getBikeBoardings();
		this.alightings = boardalight.getAlightings();
		this.currentLoad = boardalight.getCurrentLoad();
		this.loadCount = boardalight.getLoadCount();
		this.loadType = boardalight.getLoadType();
		this.rackDown = boardalight.getRackDown();
		this.bikeBoardings = boardalight.getBikeBoardings();
		this.bikeAlightings = boardalight.getBikeAlightings();
		this.rampUsed = boardalight.getRampUsed();
		this.rampBoardings = boardalight.getRampBoardings();
		this.rampAlightings = boardalight.getRampAlightings();
		this.serviceArrivalTime = boardalight.getServiceArrivalTime();
		this.serviceDepartureTime = boardalight.getServiceDepartureTime();
		this.source = boardalight.getSource();
	}

	public String getTripId() {
		return tripId;
	}

	public String getStopId() {
		return stopId;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public Integer getStopSequence() {
		return stopSequence;
	}

	public String getRecordUse() {
		return recordUse;
	}

	public Integer getScheduleRelationship() {
		return scheduleRelationship;
	}

	public Integer getBoardings() {
		return boardings;
	}

	public Integer getAlightings() {
		return alightings;
	}

	public Integer getCurrentLoad() {
		return currentLoad;
	}

	public Integer getLoadCount() {
		return loadCount;
	}

	public Integer getLoadType() {
		return loadType;
	}

	public Boolean getRackDown() {
		return rackDown;
	}

	public Integer getBikeBoardings() {
		return bikeBoardings;
	}

	public Integer getBikeAlightings() {
		return bikeAlightings;
	}

	public Boolean getRampUsed() {
		return rampUsed;
	}

	public Integer getRampBoardings() {
		return rampBoardings;
	}

	public Integer getRampAlightings() {
		return rampAlightings;
	}

	public String getServiceArrivalTime() {
		return serviceArrivalTime;
	}

	public String getServiceDepartureTime() {
		return serviceDepartureTime;
	}

	public Integer getSource() {
		return source;
	}

}
