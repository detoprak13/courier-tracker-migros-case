package com.migros.couriertracker.data.repository;

import com.migros.couriertracker.data.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;

public interface CourierRepository extends JpaRepository<Courier, Long> {

	@Query("SELECT (count(c) = 0) FROM couriers c JOIN c.locationList cl WHERE (cl.date > :date) AND (cl.nearStoreName = :nearStoreName)")
	Boolean isNewEntry(@Param("date")ZonedDateTime date, @Param("nearStoreName") String nearStoreName);
}
