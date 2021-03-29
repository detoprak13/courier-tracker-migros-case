package com.migros.couriertracker.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name="couriers")
public class Courier {
	private Long id;
	private String name;
	private Double totalDistanceTraveled;
	private List<CourierLocation> locationList;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "total_distance", nullable = false)
	public Double getTotalDistanceTraveled() {
		return totalDistanceTraveled;
	}

	public void setTotalDistanceTraveled(Double totalDistanceTraveled) {
		this.totalDistanceTraveled = totalDistanceTraveled;
	}

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "courier_locations", joinColumns = @JoinColumn(name = "courier_id"))
	public List<CourierLocation> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<CourierLocation> locationList) {
		this.locationList = locationList;
	}

	@Override
	public String toString() {
		return "Courier{" +
				"id=" + id +
				", name='" + name + '\'' +
				", totalDistanceTraveled=" + totalDistanceTraveled +
				", locationList=" + locationList +
				'}';
	}
}
