package com.migros.couriertracker.dto.request;


import javax.validation.constraints.NotBlank;

public class CreateCourierRequest {
	@NotBlank(message = "name is mandatory")
	private String name;

	public String getName() {
		return name;
	}
}
