package com.ant.linker.module.statistics.service;

import java.util.List;

import com.ant.linker.module.shared.dto.security.UserSignUpDto;
import com.ant.linker.module.shared.dto.statistics.DataStatisticElementDto;

public interface IStatisticsService {

	public List<DataStatisticElementDto> getQuoteStatistics(UserSignUpDto commercialAccount);
	public List<DataStatisticElementDto> getQuoteTimeLineStatistics(UserSignUpDto commercialAccount);
	
}
