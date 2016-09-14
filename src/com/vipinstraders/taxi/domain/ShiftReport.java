package com.vipinstraders.taxi.domain;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ShiftReport {

	private int id;
	private Date date;
	private Date finishDate;
	private int carId;
	private int driverId;
	private double meterRevenue;
	private double ownerRevenue;
	private double ownerSubsidy;
	private double bailmentFee;
	private double paperVoucher;
	private double fuelReceipt;
	private double onlineReceipt;
	private double total;
	
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

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		return gson.toJson(this);
	}

}
