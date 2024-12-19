package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Cab;
import com.project.repository.CabRepository;

import java.util.List;

@Service
public class CabService {

    @Autowired
    private CabRepository cabRepository;
    
    

    // Create a new Cab
    public Cab createCab(Cab cab) {
        return cabRepository.save(cab);
    }

    // Retrieve all Cabs
    public List<Cab> getAllCabs() {
        return cabRepository.findAll();
    }

    // Retrieve a Cab by its ID
    public Cab getCabById(Long id) {
        return cabRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cab not found with id: " + id));
    }

    // Update an existing Cab
    public Cab updateCab(Long id, Cab cabDetails) {
        Cab cab = cabRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cab not found with id: " + id));
        cab.setId(cabDetails.getId());
        cab.setSource(cabDetails.getSource());
        cab.setDestination(cabDetails.getDestination());
        cab.setType(cabDetails.getType());
        cab.setKms(cabDetails.getKms());
        cab.setCost(cabDetails.getCost());
        
        return cabRepository.save(cab);
    }

    // Delete a Cab by its ID
    public void deleteCab(Long id) {
        if (!cabRepository.existsById(id)) {
            throw new RuntimeException("Cab not found with id: " + id);
        }
        cabRepository.deleteById(id);
    }
}