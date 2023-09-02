package com.aditya.summaryapi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("summary")
public class ApiController {

		@PostMapping("getSummary")
		public String getSummary(@RequestBody String tabUrl) {
			return SummaryService.getSummary(tabUrl);	
		}
		
		@PostMapping("getImptPoints")
		public String getImptPoints(@RequestBody String tabUrl) {
			return ImpPointsService.getImpPoints(tabUrl);	
		}	
		
	}