package com.migros.couriertracker.data.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.ZonedDateTime;

@Embeddable
public class CourierLocation {
	private Double lat;
	private Double lng;
	private ZonedDateTime date;
	private String nearStoreName;
	private Boolean newEntry;

	public CourierLocation() {
	}

	public CourierLocation(Double lat, Double lng, ZonedDateTime date, String nearStoreName, Boolean newEntry) {
		this.lat = lat;
		this.lng = lng;
		this.date = date;
		this.nearStoreName = nearStoreName;
		this.newEntry = newEntry;
	}

	@Column(name = "lat", nullable = false)
	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	@Column(name = "lng", nullable = false)
	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	@Column(name ="date", nullable = false)
	public ZonedDateTime getDate() {
		return date;
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	@Column(name = "near_store_name")
	public String getNearStoreName() {
		return nearStoreName;
	}

	public void setNearStoreName(String nearStoreName) {
		this.nearStoreName = nearStoreName;
	}

	@Column(name = "new_entry")
	public Boolean getNewEntry() {
		return newEntry;
	}

	public void setNewEntry(Boolean newEntry) {
		this.newEntry = newEntry;
	}
}
