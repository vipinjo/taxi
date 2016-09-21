package com.vipinstraders.taxi.domain;

public class ShiftReportDetails {
	
	private double totalMeterRevenue;
	private double totolOwnerRevenue;
	private double totalDriverRevenue;
	private double paperVoucher;
	private double onlineReceipts;
	private double accountVoucher;
	private double fuelRecipts;
	private double total;
	
	public ShiftReportDetails() {
		
	}

	public double getTotalMeterRevenue() {
		return totalMeterRevenue;
	}

	public void setTotalMeterRevenue(double totalMeterRevenue) {
		this.totalMeterRevenue = totalMeterRevenue;
	}

	public double getTotolOwnerRevenue() {
		return totolOwnerRevenue;
	}

	public void setTotolOwnerRevenue(double totolOwnerRevenue) {
		this.totolOwnerRevenue = totolOwnerRevenue;
	}

	public double getTotalDriverRevenue() {
		return totalDriverRevenue;
	}

	public void setTotalDriverRevenue(double totalDriverRevenue) {
		this.totalDriverRevenue = totalDriverRevenue;
	}

	public double getPaperVoucher() {
		return paperVoucher;
	}

	public void setPaperVoucher(double paperVoucher) {
		this.paperVoucher = paperVoucher;
	}

	public double getOnlineReceipts() {
		return onlineReceipts;
	}

	public void setOnlineReceipts(double onlineReceipts) {
		this.onlineReceipts = onlineReceipts;
	}

	public double getAccountVoucher() {
		return accountVoucher;
	}

	public void setAccountVoucher(double accountVoucher) {
		this.accountVoucher = accountVoucher;
	}

	public double getFuelRecipts() {
		return fuelRecipts;
	}

	public void setFuelRecipts(double fuelRecipts) {
		this.fuelRecipts = fuelRecipts;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
