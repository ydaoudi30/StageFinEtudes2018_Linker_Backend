package com.ant.linker.module.shared.factory.statistics;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.ant.linker.module.shared.dto.statistics.DataStatisticElementDto;

public interface IStatisticsFactory {

	public List<DataStatisticElementDto> makeStatisticElements(Map<String, Integer> dataMap);
	
	public DataStatisticElementDto makeStatisticElement(String label, Integer value);
	
	public List<DataStatisticElementDto> makeTimeStatisticElements(Map<Calendar, Integer> dataMap);
}
