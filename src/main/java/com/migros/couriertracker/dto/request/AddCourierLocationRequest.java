package com.migros.couriertracker.dto.request;

import javax.validation.constraints.*;
import java.time.ZonedDateTime;

public class AddCourierLocationRequest {
	@NotNull(message = "courier id is mandatory")
	private Long courierId;
	@NotNull(message = "latitude is mandatory")
	@DecimalMin(value = "-90", message = "latitude must be between -90 and 90")
	@DecimalMax(value = "90", message = "latitude must be between -90 and 90")
	private Double lat;
	@NotNull(message = "longitude is mandatory")
	@DecimalMin(value = "-180", message = "longitude must be between -180 and 180")
	@DecimalMax(value = "180", message = "longitude must be between -180 and 180")
	private Double lng;
	@NotNull(message = "Date is mandatory")
	private ZonedDateTime date;

	public Long getCourierId() {
		return courierId;
	}


	public Double getLat() {
		return lat;
	}


	public Double getLng() {
		return lng;
	}


	public ZonedDateTime getDate() {
		return date;
	}

}
