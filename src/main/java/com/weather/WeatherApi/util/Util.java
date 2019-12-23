package com.weather.WeatherApi.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.weather.WeatherApi.exceptions.DefaultException;

public class Util {
	
	public static Date DateParser(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsedDate = null;
		Date sqlDate = null;
		try {
			parsedDate = dateFormat.parse(date);
		    sqlDate = new Date(parsedDate.getTime());
		} catch (ParseException e) {
			throw new DefaultException("Please enter proper date format");
		}
		return sqlDate;	
	}
	
}
