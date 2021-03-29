package com.migros.couriertracker.controller;

import com.migros.couriertracker.data.entity.CourierLocation;
import com.migros.couriertracker.dto.request.AddCourierLocationRequest;
import com.migros.couriertracker.dto.request.CreateCourierRequest;
import com.migros.couriertracker.dto.response.AddCourierLocationResponse;
import com.migros.couriertracker.dto.response.CreateCourierResponse;
import com.migros.couriertracker.dto.response.GetTotalTravelDistanceResponse;
import com.migros.couriertracker.service.CourierManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("couriers")
public class CourierRestController {
	private final CourierManager service;

	public CourierRestController(CourierManager service) {
		this.service = service;
	}

	@PostMapping
	public CreateCourierResponse create(@RequestBody @Validated CreateCourierRequest createCourierRequest) {
		return new CreateCourierResponse(service.create(createCourierRequest.getName()));
	}

	@PostMapping("/locations")
	public AddCourierLocationResponse addCourierLocation(@RequestBody @Validated AddCourierLocationRequest addCourierLocationRequest) {
		CourierLocation courierLocation = service.addCourierLocation(addCourierLocationRequest.getCourierId(), addCourierLocationRequest.getLat(),
				addCourierLocationRequest.getLng(), addCourierLocationRequest.getDate());
		return new AddCourierLocationResponse(courierLocation.getNewEntry(), courierLocation.getNearStoreName());
	}

	@GetMapping("/{id}/travelDistance")
	public GetTotalTravelDistanceResponse getTotalTravelDistance(@PathVariable Long id) {
		return new GetTotalTravelDistanceResponse(service.getTotalDistanceTraveled(id));
	}
}
