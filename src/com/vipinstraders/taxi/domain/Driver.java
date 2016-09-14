package com.vipinstraders.taxi.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Driver {
	
	private int id;
	private String familyName;
	private String givenName;
	private String dc;
	private String abn;
	
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public Driver() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public String getABN() {
		return abn;
	}

	public void setABN(String abn) {
		this.abn = abn;
	}
	
	@Override
	public String toString() {
		return gson.toJson(this);
	}

}
