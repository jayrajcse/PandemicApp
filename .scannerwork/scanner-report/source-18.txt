package com.cts.covid.PandemicApp.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class DistrictData {
	String district;
	int active;
	int confirmed;
	int deceased;
	int recovered;

}
