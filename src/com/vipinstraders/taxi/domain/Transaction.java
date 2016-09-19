package com.vipinstraders.taxi.domain;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Transaction {
	
 	private int id;
 	private Date date;
 	private ExpenseType expenseType;
 	private double expenseAmount;
 	private int meterReading;
 	private String description;
 	
 	static Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").setPrettyPrinting().create();
 	
 	public Transaction() {
 		
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

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

	public double getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(double expenseAmout) {
		this.expenseAmount = expenseAmout;
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
	
	@Override
	public String toString() {
		return gson.toJson(this);
	}

}
