package com.cts.covid.PandemicApp.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataClass {
String cases;
int reportedcases;
int activecases;
int deceasedcases;
int recoveredcases;

}
