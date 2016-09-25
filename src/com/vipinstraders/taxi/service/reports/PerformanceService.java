package com.vipinstraders.taxi.service.reports;

import java.util.List;

import com.vipinstraders.taxi.domain.Performance;
import com.vipinstraders.taxi.domain.PerformanceConsidated;
import com.vipinstraders.taxi.object.criteria.PerformanceSearchCriteria;

public interface PerformanceService {
	
	public List<Performance> getPerformaceDetails(PerformanceSearchCriteria criteria);
	
	public PerformanceConsidated getPerformanceConsolidatedDetails(PerformanceSearchCriteria criteria);

}
