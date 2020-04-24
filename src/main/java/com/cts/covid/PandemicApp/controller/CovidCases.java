package com.cts.covid.PandemicApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.covid.PandemicApp.model.WrapperData;
import com.cts.covid.PandemicApp.service.StateStatsService;

@RestController
public class CovidCases {

	@Autowired
public StateStatsService stats;	
	
@PostMapping("/totalcases")	
public List<WrapperData> getTotalCases() {
	return stats.getTotalCases();
}

@PutMapping("/captureuserevents")
public ResponseEntity<String> writeUserEvents(@RequestBody @Valid UserRequest userReq){
	return new ResponseEntity<>("Daa Persisted Successfully!", HttpStatus.OK);
}
}
