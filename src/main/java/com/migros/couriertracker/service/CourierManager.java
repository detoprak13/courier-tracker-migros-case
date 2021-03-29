package com.migros.couriertracker.service;

import com.migros.couriertracker.data.dto.Store;
import com.migros.couriertracker.data.entity.CourierLocation;
import com.migros.couriertracker.data.jsonMapper.StoreJsonMapper;
import com.migros.couriertracker.data.service.CourierService;
import com.migros.couriertracker.util.DistanceCalculator;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class CourierManager {
	private final StoreJsonMapper storeJsonMapper;
	private final CourierService courierService;

	public CourierManager(StoreJsonMapper storeJsonMapper, CourierService courierService) {
		this.storeJsonMapper = storeJsonMapper;
		this.courierService = courierService;
	}

	public Long create(String name) {
		return courierService.create(name).getId();
	}

	public CourierLocation addCourierLocation(Long courierId, Double lat, Double lng, ZonedDateTime date) {
		List<Store> stores = storeJsonMapper.getStores();
		String nearStoreName = null;
		for (Store store : stores) {
			Double distance = DistanceCalculator.distance(store.getLat(), store.getLng(), lat, lng);
			if (distance.compareTo(0.01) <= 0) {
				nearStoreName = store.getName();
			}
		}
		CourierLocation courierLocation = courierService.addTravelLocation(courierId, lat, lng, date, nearStoreName);
		return courierLocation;
	}

	public Double getTotalDistanceTraveled(Long id) {
		return courierService.findById(id).getTotalDistanceTraveled();
	}
}
