package com.vipinstraders.taxi.domain;

import java.util.Date;

public class PerformanceConsidated {
	
	private Date startDate;
	private Date endDate;
	private double driverFees;
	private double ownerIncome;
	private double fuelCost;
	private double maintanenceCost;
	private double totalEarnings;
	private double meterRevenue;
	
	public PerformanceConsidated() {
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

	public double getDriverFees() {
		return driverFees;
	}

	public void setDriverFees(double driverFees) {
		this.driverFees = driverFees;
	}

	public double getOwnerIncome() {
		return ownerIncome;
	}

	public void setOwnerIncome(double ownerIncome) {
		this.ownerIncome = ownerIncome;
	}

	public double getFuelCost() {
		return fuelCost;
	}

	public void setFuelCost(double fuelCost) {
		this.fuelCost = fuelCost;
	}

	public double getMaintanenceCost() {
		return maintanenceCost;
	}

	public void setMaintanenceCost(double maintanenceCost) {
		this.maintanenceCost = maintanenceCost;
	}

	public double getTotalEarnings() {
		return totalEarnings;
	}

	public void setTotalEarnings(double totalEarnings) {
		this.totalEarnings = totalEarnings;
	}

	public double getMeterRevenue() {
		return meterRevenue;
	}

	public void setMeterRevenue(double meterRevenue) {
		this.meterRevenue = meterRevenue;
	}

}
