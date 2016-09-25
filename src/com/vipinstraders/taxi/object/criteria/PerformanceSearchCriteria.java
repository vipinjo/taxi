package com.vipinstraders.taxi.object.criteria;

import java.util.Date;

public class PerformanceSearchCriteria {

	private Date startDate;
	private Date endDate;
	
	public PerformanceSearchCriteria() {
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
