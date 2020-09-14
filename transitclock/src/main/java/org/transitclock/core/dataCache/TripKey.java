package org.transitclock.core.dataCache;

import java.util.Date;
/**
 * @author Sean Og Crudden
 * 
 */
public class TripKey implements java.io.Serializable {
	/**
	 * Needs to be serializable to add to cache
	 */
	private static final long serialVersionUID = 5029823633051153715L;
	private String tripId;
	private String routeId;
	
	private Date tripStartDate;
	private Integer startTime;

	
	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	/**
	 * @return the tripId
	 */
	public String getTripId() {
		return tripId;
	}
	
	/**
	 * @return the tripStartDate
	 */
	public Date getTripStartDate() {
		return tripStartDate;
	}
	/**
	 * @return the startTime
	 */
	public Integer getStartTime() {
		return startTime;
	}
	public TripKey(String routeId, String tripId,  Date tripStartDate,
			Integer startTime) {
		super();
		this.tripId = tripId;	
		
		this.tripStartDate = tripStartDate;
		this.startTime = startTime;
	}



	public void setStartTime(Integer time) {
		// TODO Auto-generated method stub
		this.startTime=time;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((routeId == null) ? 0 : routeId.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((tripStartDate == null) ? 0 : tripStartDate.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "TripKey [tripId=" + tripId + ", routeId=" + routeId + ", tripStartDate=" + tripStartDate
				+ ", startTime=" + startTime + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TripKey other = (TripKey) obj;
		if (routeId == null) {
			if (other.routeId != null)
				return false;
		} else if (!routeId.equals(other.routeId))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (tripStartDate == null) {
			if (other.tripStartDate != null)
				return false;
		} else if (!tripStartDate.equals(other.tripStartDate))
			return false;
		return true;
	}
}
