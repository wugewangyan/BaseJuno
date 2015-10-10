package com.juno.ws.springws.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.juno.ws.springws.WeatherService;
import com.juno.ws.springws.bean.TemperatureInfo;

@Component
public class WeatherServiceImpl implements WeatherService {

	public List<TemperatureInfo> getTemperatures(String city, List<Date> dates) {
		List<TemperatureInfo> temperatures = new ArrayList<TemperatureInfo>();
		for(Date date : dates){
			temperatures.add(new TemperatureInfo(city, date, 5.0, 10.0, 8.0));
		}
		
		return temperatures;
	}

}
