package org.transitclock.db.structs;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.transitclock.db.hibernate.HibernateUtils;

public class AvlReportId implements Serializable{
	
	
	public AvlReportId() {
		super();
		
	}

	private static final long serialVersionUID = -211503674669677285L;

	@Column(length=HibernateUtils.DEFAULT_ID_SIZE) 
	String vehicleId = null;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date time = null;

	public AvlReportId(String vehicleId, Date time) {
		super();
		this.vehicleId = vehicleId;
		this.time = time;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((vehicleId == null) ? 0 : vehicleId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvlReportId other = (AvlReportId) obj;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (vehicleId == null) {
			if (other.vehicleId != null)
				return false;
		} else if (!vehicleId.equals(other.vehicleId))
			return false;
		return true;
	}
}
