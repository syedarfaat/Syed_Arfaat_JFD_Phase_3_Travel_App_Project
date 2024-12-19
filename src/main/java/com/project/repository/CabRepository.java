package com.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Cab;


@Repository
public interface CabRepository extends JpaRepository<Cab, Long> {
    // You can add custom query methods here if needed
	
	Optional<Cab> findById(Long id);
}
