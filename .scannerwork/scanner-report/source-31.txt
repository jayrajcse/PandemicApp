package com.cts.covid.PandemicApp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cts.covid.PandemicApp.model.ChildrenClass;
import com.cts.covid.PandemicApp.model.DataClass;
import com.cts.covid.PandemicApp.model.DistName;
import com.cts.covid.PandemicApp.model.DistrictData;
import com.cts.covid.PandemicApp.model.StateName;
import com.cts.covid.PandemicApp.model.States;
import com.cts.covid.PandemicApp.model.WrapperChildren;
import com.cts.covid.PandemicApp.model.WrapperData;


@Component
public class StateStatsService {
	
public final String END_POINT_COVID_CASES="https://api.covid19india.org/v2/state_district_wise.json";


@Bean
public RestTemplate restTemplate() {
	return new RestTemplate();
}

 public List<WrapperData> getTotalCases(){
//	 CovidCases c1=new CovidCases(0,1,0,0,"North and Middle Andaman");
//	 CovidCases c2=new CovidCases(0,1,0,0,"South Andaman");
//	 DistrictStats d1=new DistrictStats(Arrays.asList(c1,c2),"Andaman and Nicobar Islands");
//	 
//	 
//	 c1=new CovidCases(49,1,2,3,"Anantapur");
//	 c2=new CovidCases(31,36,3,2,"Chittoor");
//	 DistrictStats d2=new DistrictStats(Arrays.asList(c1,c2),"Andhra Pradesh");
//	 
	 
	 List<States> stateWiseData=new ArrayList<States>();
	 
		try {
			ResponseEntity<List<States>> response  = restTemplate().exchange(
					END_POINT_COVID_CASES, 
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<States>>() {});
			 stateWiseData=response.getBody();
			System.out.println(stateWiseData);
		}
		 catch (Exception e) {
				e.printStackTrace();
			}
		
		
		/*
		 * List<WrapperData> clonedList =stateWiseData.stream().collect( .map(item ->
		 * {return (new WrapperData(item)).setLifeCycle("someLifeCycle");})
		 * .collect(Collectors.toList());
		 * 
		 * 
		 * stateWiseData.stream().map(x->x.getDistrictData().forEach(y->{ DistName
		 * c1=new
		 * DistName(y.getDistrict(),y.getConfirmed(),y.getActive(),y.getDeceased(),y.
		 * getRecovered()); c1.set WrapperChildren wch1=new WrapperChildren(c1); }));
		 */
		 final IntWrapper dWrapper = new IntWrapper();
		 List<WrapperData> totalList1=new ArrayList<WrapperData>();
		
		 stateWiseData.stream().forEach(p ->
		   {
			   
				 List<WrapperChildren> cList=p.getDistrictData().stream().map(x ->new WrapperChildren(new DistName(x.getDistrict(),x.getConfirmed(),x.getActive(),x.getDeceased(),x.getRecovered()))).collect(Collectors.toList());
				 Integer reported = cList.stream()
						 .mapToInt(o -> o.getData().getReportedcases()).sum();
				 
				 dWrapper.value +=reported;
				 Integer active = cList.stream()
						 .mapToInt(o -> o.getData().getActivecases()).sum();
				 Integer deceased = cList.stream()
						 .mapToInt(o -> o.getData().getDeceasedcases()).sum();
				 Integer recovered = cList.stream()
						 .mapToInt(o -> o.getData().getRecoveredcases()).sum();
				 DataClass dataClass2=new DataClass(p.getState(),reported,active,deceased,recovered);
				 WrapperData data2=new WrapperData(dataClass2,cList,32);
				 totalList1.add(data2);
		   });
			
		
		 int reportedCases = dWrapper.value;
		 System.out.println("reported cases:"+reportedCases);
		System.out.println("District Data:"+ totalList1.size());
		
		
//	 DistName c1=new DistName("North and Middle Andaman",0,1,0,0);
//	 WrapperChildren wch1=new WrapperChildren(c1); 
//	 DistName c2=new DistName("South Andaman",0,1,0,0);
//	 WrapperChildren wch2=new WrapperChildren(c2); 
//	 List<WrapperChildren> cList=Arrays.asList(wch1,wch2);
//	 DataClass dataClass1=new DataClass("Andaman and Nicobar Islands",0,2,0,0);
//	 
//	 
//	 
//	 
//	 WrapperData data1=new WrapperData(dataClass1,cList,32);
//	 
//	 c1=new DistName("Anantapur",49,1,2,3);
//	 c2=new DistName("Chittoor",31,36,3,2);
//	 wch1=new WrapperChildren(c1);
//	 wch2=new WrapperChildren(c2);
//	 
//	 cList=Arrays.asList(wch1,wch2);
//	 DataClass dataClass2=new DataClass("Andhra Pradesh",0,2,0,0);
//	 
//	 WrapperData data2=new WrapperData(dataClass2,cList,32);

	 
	 
	 return totalList1;
 }

 public class IntWrapper {
    public int value;
 }
}
