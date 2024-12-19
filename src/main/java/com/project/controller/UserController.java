package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.model.Booking;
import com.project.model.Cab;
import com.project.model.User; // Import your User model
import com.project.repository.BookingRepository;
import com.project.repository.CabRepository;
import com.project.repository.UserRepository; // Import your User repository

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private CabRepository cabRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository; // Inject UserRepository

    @GetMapping("/cabs")
    public ResponseEntity<List<Cab>> getAllCabs() {
        List<Cab> cabs = cabRepository.findAll();
        return ResponseEntity.ok(cabs);
    }

    @PostMapping("/bookings")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking savedBooking = bookingRepository.save(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}