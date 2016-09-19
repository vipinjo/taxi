package com.vipinstraders.taxi.object.criteria;

import java.util.Date;

public class TransactionSearchCriteria {
	
	private Date startDate;
	private Date endDate;
	
	public TransactionSearchCriteria() {
	}
	
	public TransactionSearchCriteria(Date startDate, Date endDate) {
	    this.startDate = startDate;
	    this.endDate = endDate;
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
