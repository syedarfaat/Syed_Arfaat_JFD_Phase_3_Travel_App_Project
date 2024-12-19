package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Booking;
import com.project.model.Cab;
import com.project.service.BookingService;
import com.project.service.CabService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CabService cabService;

    // Booking CRUD operations
    @PostMapping(path="/bookings", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking savedBooking = bookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/bookings/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails) {
        Booking updatedBooking = bookingService.updateBooking(id, bookingDetails);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    // Cab CRUD operations
    @PostMapping("/cabs")
    public ResponseEntity<Cab> createCab(@RequestBody Cab cab) {
        Cab savedCab = cabService.createCab(cab);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCab);
    }

    @GetMapping("/cabs")
    public ResponseEntity<List<Cab>> getAllCabs() {
        List<Cab> cabs = cabService.getAllCabs();
        return ResponseEntity.ok(cabs);
    }

    @GetMapping("/cabs/{id}")
    public ResponseEntity<Cab> getCabById(@PathVariable Long id) {
        Cab cab = cabService.getCabById(id);
        return ResponseEntity.ok(cab);
    }

    @PutMapping("/cabs/{id}")
    public ResponseEntity<Cab> updateCab(@PathVariable Long id, @RequestBody Cab cabDetails) {
        Cab updatedCab = cabService.updateCab(id, cabDetails);
        return ResponseEntity.ok(updatedCab);
    }

    @DeleteMapping("/cabs/{id}")
    public ResponseEntity<Void> deleteCab(@PathVariable Long id) {
        cabService.deleteCab(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/users/{userId}/bookings")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(bookings);
    }
}