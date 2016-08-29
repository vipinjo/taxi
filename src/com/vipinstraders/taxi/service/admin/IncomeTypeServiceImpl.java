package com.vipinstraders.taxi.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vipinstraders.taxi.dao.IncomeTypeDao;
import com.vipinstraders.taxi.domain.IncomeType;

@Component
public class IncomeTypeServiceImpl implements IncomeTypeService {

	private IncomeTypeDao incomeTypeDao;

	@Autowired
	public IncomeTypeServiceImpl(IncomeTypeDao incomeTypeDao) {
		this.incomeTypeDao = incomeTypeDao;
	}

	@Override
	public void addIncomeType(IncomeType incomeType) {
		incomeTypeDao.createIncomeType(incomeType);
	}

	@Override
	public void editIncomeType(IncomeType incomeType) {
		incomeTypeDao.editIncomeType(incomeType);
	}

	@Override
	public void deleteIncomeType(String id) {
		incomeTypeDao.deleteIncomeType(id);
	}

	@Override
	public List<IncomeType> getAll() {
		return incomeTypeDao.getAllIncomeType();
	}

}
