package com.vipinstraders.taxi.dao;

import java.util.List;

import com.vipinstraders.taxi.domain.IncomeType;

public interface IncomeTypeDao {
	
	public void createIncomeType(IncomeType incomeType);
	
	public void editIncomeType(IncomeType incomeType);
	
	public List<IncomeType> getAllIncomeType();
	
	public void deleteIncomeType(String id);

}
