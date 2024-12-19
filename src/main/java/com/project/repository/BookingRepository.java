package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // You can add custom query methods here if needed
	 List<Booking> findByUserId(Long userId);
}
