package com.vipinstraders.taxi.object.criteria;

import java.util.Date;

public class ShiftReportSearchCriteria {
	
	private Date startDate;
	private Date endDate;
	private int driverId;
	
	public ShiftReportSearchCriteria() {
		setDriverId(-1);
	}
	
	public ShiftReportSearchCriteria(Date startDate, Date finishDate) {
		setStartDate(startDate);
		setEndDate(finishDate);
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date statDate) {
		this.startDate = statDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date finishDate) {
		this.endDate = finishDate;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

}
