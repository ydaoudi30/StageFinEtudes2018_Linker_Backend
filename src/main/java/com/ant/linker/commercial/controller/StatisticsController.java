package com.ant.linker.commercial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.linker.module.shared.dto.security.UserSignUpDto;
import com.ant.linker.module.shared.dto.statistics.DataStatisticElementDto;
import com.ant.linker.module.statistics.service.IStatisticsService;

@RestController
@RequestMapping("/cmr/statistics")
public class StatisticsController {

	@Autowired
	private IStatisticsService statisticsService;
	
	@PostMapping("/quote")
	public List<DataStatisticElementDto> getQuoteStatistics(@RequestBody UserSignUpDto commercialDto) {
		return statisticsService.getQuoteStatistics(commercialDto);
	}
	
	@PostMapping("/quote/timeline")
	public List<DataStatisticElementDto> getQuoteTimeLineStatistics(@RequestBody UserSignUpDto commercialDto) {
		return statisticsService.getQuoteTimeLineStatistics(commercialDto);
	}
	
	
}
