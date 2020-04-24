package com.cts.covid.PandemicApp.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WrapperData {
	private DataClass data;
	private List<WrapperChildren> children;
	int height;
	
	
}
