package com.cts.covid.PandemicApp.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistName {
	String cases;
	int reportedcases;
	int activecases;
	int deceasedcases;
	int recoveredcases;

}
