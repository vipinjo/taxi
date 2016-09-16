package com.vipinstraders.taxi.object.criteria;

import java.util.Date;

public class ExpenseSearchCriteria {
	
	private Date startDate;
	private Date endDate;
	
	public ExpenseSearchCriteria() {
	}
	
	public ExpenseSearchCriteria(Date startDate, Date finishDate) {
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

}
