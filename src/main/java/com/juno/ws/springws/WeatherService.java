package com.juno.ws.springws;

import java.util.Date;
import java.util.List;

import com.juno.ws.springws.bean.TemperatureInfo;

public interface WeatherService {
	public List<TemperatureInfo> getTemperatures(String city, List<Date> dates);
}
