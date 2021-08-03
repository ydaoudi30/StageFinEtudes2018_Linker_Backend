package com.ant.linker.module.shared.factory.statistics;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ant.linker.module.shared.dto.statistics.DataStatisticElementDto;

@Component
public class StatisticsFactory implements IStatisticsFactory {
	
	public List<DataStatisticElementDto> makeStatisticElements(Map<String, Integer> dataMap){
		List<DataStatisticElementDto> dataStatistics = new ArrayList<>();
		dataMap.entrySet().forEach( data -> {
			dataStatistics.add(makeStatisticElement(data.getKey(), data.getValue()));
		});
		return dataStatistics;
	}
	
	public DataStatisticElementDto makeStatisticElement(String label, Integer value){
		return new DataStatisticElementDto(label, value);
	}

	@Override
	public List<DataStatisticElementDto> makeTimeStatisticElements(Map<Calendar, Integer> dataMap) {
		List<DataStatisticElementDto> dataStatistics = new ArrayList<>();
		dataMap.entrySet().forEach( data -> {
			Calendar mCalendar = data.getKey();
			String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
			int year = mCalendar.get(Calendar.YEAR);
			String date = month + " - " + year;
			dataStatistics.add(makeStatisticElement(date, data.getValue()));
		});
		return dataStatistics;
	}
}
