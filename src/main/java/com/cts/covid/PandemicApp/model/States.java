package com.cts.covid.PandemicApp.model;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;


@Data
@Component
public class States {
	
	private String state;
	List<DistrictData> districtData;
}
