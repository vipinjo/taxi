package com.vipinstraders.taxi.domain;

import java.util.Date;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Performance implements Comparable<Performance> {
	
	private String id;
	private Date date;
	private String driverName;
	private double meterRevenue;
	private double driverFees;
	private double ownerIncome;
	private double fuelCost;
	private double maintanenceCost;
	private double total;
	private String maintanenceDescription;
	private int meterReading;
	private String maintanenceType;
	
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public Performance() {
		setId(UUID.randomUUID().toString());
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getMaintanenceDescription() {
		return maintanenceDescription;
	}

	public void setMaintanenceDescription(String maintanenceDescription) {
		this.maintanenceDescription = maintanenceDescription;
	}

	public int getMeterReading() {
		return meterReading;
	}

	public void setMeterReading(int meterReading) {
		this.meterReading = meterReading;
	}

	public String getMaintanenceType() {
		return maintanenceType;
	}

	public void setMaintanenceType(String maintanenceType) {
		this.maintanenceType = maintanenceType;
	}

	public double getMeterRevenue() {
		return meterRevenue;
	}

	public void setMeterRevenue(double meterRevenue) {
		this.meterRevenue = meterRevenue;
	}

	@Override
	public int compareTo(Performance o) {
		return this.date.compareTo(o.date);
	}
	
	@Override
	public String toString() {
		return gson.toJson(this);
	}

}
