package com.migros.couriertracker.dto.response;

public class AddCourierLocationResponse {
	private Boolean isNewEntry;
	private String nearStoreName;

	public AddCourierLocationResponse(Boolean isNewEntry, String nearStoreName) {
		this.isNewEntry = isNewEntry;
		this.nearStoreName = nearStoreName;
	}

	public Boolean getIsNewEntry() {
		return isNewEntry;
	}

	public void setIsNewEntry(Boolean isNewEntry) {
		this.isNewEntry = isNewEntry;
	}

	public String getNearStoreName() {
		return nearStoreName;
	}

	public void setNearStoreName(String nearStoreName) {
		this.nearStoreName = nearStoreName;
	}
}
