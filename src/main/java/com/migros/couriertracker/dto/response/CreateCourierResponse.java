package com.migros.couriertracker.dto.response;

import javax.validation.constraints.NotBlank;

public class CreateCourierResponse {
	Long id;

	public CreateCourierResponse(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
