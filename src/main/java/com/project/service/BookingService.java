package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Booking;
import com.project.model.Cab;
import com.project.model.User;
import com.project.repository.BookingRepository;
import com.project.repository.CabRepository;
import com.project.repository.UserRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CabRepository cabRepository;
    
    @Autowired
    private CabService cabService;
    
    @Autowired
    private UserRepository userRepository;
    public Booking createBooking(Booking booking) {
        // Fetch the cab by ID to associate it with the booking
        Cab cab = cabRepository.findById(booking.getCab().getId())
                .orElseThrow(() -> new RuntimeException("Cab not found"));

        // Fetch the user by ID to associate it with the booking
        User user = userRepository.findById(booking.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Debugging: Print cab details
        System.out.println("Cab Cost: " + cab.getCost());
        System.out.println("Cab Kms: " + cab.getKms());

        // Calculate fare based on cab details
        double fare = cab.getCost() * cab.getKms();
        booking.setFare(fare);

        // Debugging: Print calculated fare
        System.out.println("Calculated Fare: " + fare);
        
        booking.setCab(cab); // Associate the cab with the booking
        booking.setUser(user); // Associate the user with the booking

        return bookingRepository.save(booking); // Save the booking
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public Booking updateBooking(Long id, Booking bookingDetails) {
        // Fetch the existing booking from the repository
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Update the booking details with the new information
        booking.setUser(bookingDetails.getUser()); // Assuming you have a User object
        booking.setCab(bookingDetails.getCab());
        booking.setDateTime(bookingDetails.getDateTime());
        booking.setStatus(bookingDetails.getStatus());
        booking.setPickupLocation(bookingDetails.getPickupLocation());
        booking.setDropoffLocation(bookingDetails.getDropoffLocation());

        // Calculate the fare based on the updated cab details
        Cab cab = bookingDetails.getCab();
        if (cab != null) {
        	System.out.println(cab.getCost());
            double fare = cab.getCost() * cab.getKms();
            System.out.println(fare);
            booking.setFare(fare);
        }

        // Save the updated booking back to the repository
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Booking not found");
        }
        bookingRepository.deleteById(id);
    }
    
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}