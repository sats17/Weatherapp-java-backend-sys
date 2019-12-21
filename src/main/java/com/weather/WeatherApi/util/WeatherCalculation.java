package com.weather.WeatherApi.util;

public class WeatherCalculation {

	
	public static Double calculateSaturateVaporPressure(Double temp) {
		
		return 6.11 * 10 * (7.5 * temp / 237.3 + temp);
		
	}
	
	public static Double calculateActualVaporPressure(Double dew) {
		
		return 6.11 * 10 * (7.5 * dew / 237.3 + dew);
	}
	
	public static Double calculateRelativeHumidity(Double temp,Double dew) {
		
		return (calculateActualVaporPressure(dew) / calculateSaturateVaporPressure(temp)) * 100;
		
	}
}
