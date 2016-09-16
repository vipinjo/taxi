package com.vipinstraders.taxi.domain;

import java.util.Date;

public class Expense {
	
 	private int id;
 	private Date date;
 	private IncomeType incomeType;
 	private double amout;
 	private int meterReading;
 	private String description;
 	
 	public Expense() {
 		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public IncomeType getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(IncomeType incomeType) {
		this.incomeType = incomeType;
	}

	public double getAmout() {
		return amout;
	}

	public void setAmout(double amout) {
		this.amout = amout;
	}

	public int getMeterReading() {
		return meterReading;
	}

	public void setMeterReading(int meterReading) {
		this.meterReading = meterReading;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
