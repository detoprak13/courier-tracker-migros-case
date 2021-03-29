package com.migros.couriertracker.dto.response;

public class GetTotalTravelDistanceResponse {
	private Double totalDistanceTraveled;

	public GetTotalTravelDistanceResponse(Double totalDistanceTraveled) {
		this.totalDistanceTraveled = totalDistanceTraveled;
	}

	public Double getTotalDistanceTraveled() {
		return totalDistanceTraveled;
	}
}
