package com.migros.couriertracker.data.service;

import com.migros.couriertracker.data.entity.Courier;
import com.migros.couriertracker.data.entity.CourierLocation;
import com.migros.couriertracker.data.repository.CourierRepository;
import com.migros.couriertracker.exception.NotFoundException;
import com.migros.couriertracker.util.DistanceCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourierService {
	private final CourierRepository courierRepository;

	public CourierService(CourierRepository courierRepository) {
		this.courierRepository = courierRepository;
	}

	public List<Courier> findAll() {
		return courierRepository.findAll();
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public Courier findById(Long id) {
		Courier courier = courierRepository.findById(id).orElse(null);
		if (courier == null) {
			throw new NotFoundException("Courier is not found with id: " + id);
		}
		return courier;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public Courier create(String name) {
		Courier courier = new Courier();
		courier.setName(name);
		courier.setTotalDistanceTraveled(0.0);
		courier.setLocationList(new ArrayList<>());
		return courierRepository.save(courier);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public CourierLocation addTravelLocation(Long courierId, Double lat, Double lng, ZonedDateTime date, String nearStoreName) {
		Courier courier = findById(courierId);

		boolean isLastSameStore = true;
		try {
			CourierLocation lastLocation = courier.getLocationList().get(courier.getLocationList().size() - 1);
			Double distanceTraveled = DistanceCalculator.distance(lastLocation.getLat(), lastLocation.getLng(), lat, lng);
			courier.setTotalDistanceTraveled(distanceTraveled + courier.getTotalDistanceTraveled());
			if (nearStoreName != null) {
				isLastSameStore = nearStoreName.equals(lastLocation.getNearStoreName());
			}
		} catch (IndexOutOfBoundsException e) {
			//distance traveled = 0;
		}

		Boolean isNewEntryInLastMinute = courierRepository.isNewEntry(date.minusMinutes(1), nearStoreName);
		Boolean isNewEntry = isNewEntryInLastMinute && !isLastSameStore;

		CourierLocation courierLocation = new CourierLocation(lat, lng, date, nearStoreName, isNewEntry);
		courier.getLocationList().add(courierLocation);
		courierRepository.save(courier);
		return courierLocation;
	}
}
