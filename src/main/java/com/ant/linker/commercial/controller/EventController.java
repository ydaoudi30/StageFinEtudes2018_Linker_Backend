package com.ant.linker.commercial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.linker.module.event.service.IEventService;
import com.ant.linker.module.shared.dto.event.EventDto;
import com.ant.linker.module.shared.dto.security.UserSignUpDto;

@RestController
@RequestMapping("/cmr/event")
public class EventController {

	@Autowired
	private IEventService eventService;
	
	@PostMapping("/commercial/list")
	public List<EventDto> getCommercialEvents(@RequestBody UserSignUpDto commercial) {
		return eventService.loadEvents(commercial);
	}
}
