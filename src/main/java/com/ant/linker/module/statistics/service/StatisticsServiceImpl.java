package com.ant.linker.module.statistics.service;

import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.QuotationRequest;
import com.ant.linker.module.commercial.service.IUserService;
import com.ant.linker.module.shared.dto.security.UserSignUpDto;
import com.ant.linker.module.shared.dto.statistics.DataStatisticElementDto;
import com.ant.linker.module.shared.factory.statistics.IStatisticsFactory;

@Service
public class StatisticsServiceImpl implements IStatisticsService{
	
	@Autowired
	private IUserService userService;

	@Autowired
	private IStatisticsFactory statisticsFactory;
	
	@Override
	public List<DataStatisticElementDto> getQuoteStatistics(UserSignUpDto commercialAccount) {
		Map<String, Integer> quoteByStatusMap = new HashMap<>();
		
		Commercial commercial = userService.getCommercialByAccountEmail(commercialAccount.getEmail());
		if (commercial != null) {
			List<QuotationRequest> listeQuotationRequest = commercial.getListeQuotationRequest();
			listeQuotationRequest.forEach( quote -> {
				if(quoteByStatusMap.containsKey(quote.getStatus())) {
					Integer counter = quoteByStatusMap.get(quote.getStatus());
					counter++;
					quoteByStatusMap.put(quote.getStatus(), counter);
				} else {
					quoteByStatusMap.put(quote.getStatus(), new Integer(1));
				}
			});
		}
		
		return statisticsFactory.makeStatisticElements(quoteByStatusMap);
	}

	@Override
	public List<DataStatisticElementDto> getQuoteTimeLineStatistics(UserSignUpDto commercialAccount) {
		Map<Calendar, Integer> quoteByStatusMap = new HashMap<>();
		
		Commercial commercial = userService.getCommercialByAccountEmail(commercialAccount.getEmail());
		if (commercial != null) {
			List<QuotationRequest> listeQuotationRequest = commercial.getListeQuotationRequest();
			
			listeQuotationRequest.forEach( quote -> {
				
				Date dateRequest = quote.getDateRequest();
				Calendar mCalendar = Calendar.getInstance();
				mCalendar.setTime(dateRequest);
				
				Calendar foundedCalendar = quoteByStatusMap.entrySet().stream()
				  .filter(e -> {
					  Calendar calKey = e.getKey();
					  boolean sameMonth = calKey.get(Calendar.YEAR) == mCalendar.get(Calendar.YEAR) &&
							  calKey.get(Calendar.MONTH) == mCalendar.get(Calendar.MONTH);
					  return sameMonth;
				  })
				  .map(Map.Entry::getKey)
				  .findFirst()
				  .orElse(null);
				
				
				if(foundedCalendar != null) {
					Integer counter = quoteByStatusMap.get(foundedCalendar);
					counter++;
					quoteByStatusMap.put(foundedCalendar, counter);
				} else {
					quoteByStatusMap.put(mCalendar, new Integer(1));
				}
			});
		}
		
		Map<Calendar, Integer> result = quoteByStatusMap.entrySet().stream()
	        .sorted(Map.Entry.comparingByKey())
	        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
	                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		return statisticsFactory.makeTimeStatisticElements(result);
	}
	
	private int getMonthNumber(String monthName) {
	    return Month.valueOf(monthName.toUpperCase()).getValue();
	}
	
}


/*(obj1, obj2)-> {
	String[] obj1Parts = obj1.split("-");
	String[] obj2Parts = obj2.split("-");
	
	int monthNumber1 = getMonthNumber(obj1Parts[0]);
	int monthNumber2 = getMonthNumber(obj2Parts[0]);
	
	int year1 = getMonthNumber(obj1Parts[1]);
	int year2 = getMonthNumber(obj2Parts[2]);
	
	Date date1 = new Date(year1, monthNumber1, 0);
	Date date2 = new Date(year2, monthNumber2, 0);
	
	
	System.out.println(obj2);
	return obj1.compareTo(obj2);
}*/