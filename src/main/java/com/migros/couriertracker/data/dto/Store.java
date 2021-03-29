package com.migros.couriertracker.data.dto;

public class Store {
	private String name;
	private Double lat;
	private Double lng;

	public Store() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "Store{" +
				"name='" + name + '\'' +
				", lat='" + lat + '\'' +
				", lng='" + lng + '\'' +
				'}';
	}
}
