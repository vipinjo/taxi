package com.vipinstraders.taxi.domain;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ShiftReport {

	private int id;
	private Date date;
	private Date finishDate;
	private Driver driver;
	private Car car;
	private double meterRevenue;
	private double ownerRevenue;
	private double ownerSubsidy;
	private double bailmentFee;
	private double paperVoucher;
	private double fuelReceipt;
	private double onlineReceipt;
	private double accountVoucher;
	private double driverRevenue;
	private double driverSubsidy;
	private double total;
	private int startMeterReading;
	private int endMeterReading;
	
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public ShiftReport() {
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

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public double getMeterRevenue() {
		return meterRevenue;
	}

	public void setMeterRevenue(double meterRevenue) {
		this.meterRevenue = meterRevenue;
	}

	public double getOwnerRevenue() {
		return ownerRevenue;
	}

	public void setOwnerRevenue(double ownerRevenue) {
		this.ownerRevenue = ownerRevenue;
	}

	public double getOwnerSubsidy() {
		return ownerSubsidy;
	}

	public void setOwnerSubsidy(double ownerSubsidy) {
		this.ownerSubsidy = ownerSubsidy;
	}

	public double getBailmentFee() {
		return bailmentFee;
	}

	public void setBailmentFee(double bailmentFee) {
		this.bailmentFee = bailmentFee;
	}

	public double getPaperVoucher() {
		return paperVoucher;
	}

	public void setPaperVoucher(double paperVoucher) {
		this.paperVoucher = paperVoucher;
	}

	public double getFuelReceipt() {
		return fuelReceipt;
	}

	public void setFuelReceipt(double fuelReceipt) {
		this.fuelReceipt = fuelReceipt;
	}

	public double getOnlineReceipt() {
		return onlineReceipt;
	}

	public void setOnlineReceipt(double onlineReceipt) {
		this.onlineReceipt = onlineReceipt;
	}

	public double getAccountVoucher() {
		return accountVoucher;
	}

	public void setAccountVoucher(double accountVoucher) {
		this.accountVoucher = accountVoucher;
	}

	public double getDriverRevenue() {
		return driverRevenue;
	}

	public void setDriverRevenue(double driverRevenue) {
		this.driverRevenue = driverRevenue;
	}

	public double getDriverSubsidy() {
		return driverSubsidy;
	}

	public void setDriverSubsidy(double driverSubsidy) {
		this.driverSubsidy = driverSubsidy;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public int getStartMeterReading() {
		return startMeterReading;
	}

	public void setStartMeterReading(int startMeterReading) {
		this.startMeterReading = startMeterReading;
	}

	public int getEndMeterReading() {
		return endMeterReading;
	}

	public void setEndMeterReading(int endMeterReading) {
		this.endMeterReading = endMeterReading;
	}
	
	@Override
	public String toString() {
		return gson.toJson(this);
	}

}
