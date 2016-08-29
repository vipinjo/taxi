package com.vipinstraders.taxi.service.admin;

import java.util.List;

import com.vipinstraders.taxi.domain.IncomeType;

public interface IncomeTypeService {
	
	public void addIncomeType(IncomeType incomeType);
	
	public void editIncomeType(IncomeType incomeType);
	
	public void deleteIncomeType(String id);
	
	public List<IncomeType> getAll();

}
