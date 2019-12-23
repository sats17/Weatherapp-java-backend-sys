package com.weather.WeatherApi.util;

public class Calculation {

	
	public static Double calculateSaturateVaporPressure(Double temp) {
		
		return 6.11 * 10 * (7.5 * temp / 237.3 + temp);
		
	}
	
	public static Double calculateActualVaporPressure(Double dew) {
		
		return 6.11 * 10 * (7.5 * dew / 237.3 + dew);
	}
	
	public static Double calculateRelativeHumidity(Double temp,Double dew) {
		
		return (calculateActualVaporPressure(dew) / calculateSaturateVaporPressure(temp)) * 100;
		
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
