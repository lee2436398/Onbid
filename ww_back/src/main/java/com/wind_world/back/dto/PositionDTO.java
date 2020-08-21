package com.wind_world.back.dto;

public class PositionDTO {
	String lon;
	String lat;
	
	public PositionDTO() {
		super();
	}
	
	public PositionDTO(String lon, String lat) {
		super();
		this.lon=lon;
		this.lat=lat;
	}
	
	public void setlon(String lon) {
		this.lon=lon;
	}
	
	public String  getlon() {
		return this.lon;
	}
	
	public void setlat(String lat) {
		this.lat=lat;
	}
	
	public String getlat() {
		return this.lat;
	}
}
