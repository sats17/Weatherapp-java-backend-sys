package com.weather.WeatherApi.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author sats17
 *
 */
@Entity
@Table(name = "rainfall",uniqueConstraints=@UniqueConstraint(columnNames={"month","year", "cityid"}))
public class Rainfall {

	@Id
	private int id;
	
	@Column(name="month")
	private int month;
	
	@Column(name = "data")
	private double data;
	
	@Column(name = "year")
	private int year;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cityid", nullable = false)
	private City city;

	
	
}
