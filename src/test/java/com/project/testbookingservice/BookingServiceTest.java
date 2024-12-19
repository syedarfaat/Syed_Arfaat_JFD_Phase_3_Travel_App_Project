package com.project.testbookingservice;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.model.Booking;
import com.project.model.Cab;
import com.project.model.User;
import com.project.repository.BookingRepository;
import com.project.repository.CabRepository;
import com.project.repository.UserRepository;
import com.project.service.BookingService;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private CabRepository cabRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBooking() {
        // Mock inputs
        Cab mockCab = new Cab();
        mockCab.setId(1L);
        mockCab.setCost(10.0);
        mockCab.setKms(15.0);

        User mockUser = new User();
        mockUser.setId(1L);

        Booking mockBooking = new Booking();
        mockBooking.setCab(mockCab);
        mockBooking.setUser(mockUser);

        // Mock repository responses
        when(cabRepository.findById(1L)).thenReturn(Optional.of(mockCab));
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(bookingRepository.save(any(Booking.class))).thenReturn(mockBooking);

        // Execute the service call
        Booking createdBooking = bookingService.createBooking(mockBooking);
        
        

        // Assertions
        assertEquals(150.0, createdBooking.getFare(), 0.01); // 10 * 15 = 150
        verify(bookingRepository, times(1)).save(any(Booking.class));
        
        System.out.println("Cab Booked Successfully");
    }

    @Test
    void testCreateBookingCabNotFound() {
        // Mock inputs
        Booking mockBooking = new Booking();
        Cab mockCab = new Cab();
        mockCab.setId(999L); // Non-existent cab ID
        mockBooking.setCab(mockCab);

        // Mock repository response
        when(cabRepository.findById(999L)).thenReturn(Optional.empty());

        // Execute the service call and assert exception
        assertThrows(RuntimeException.class, () -> bookingService.createBooking(mockBooking));
    }
    
    // Additional tests for other methods can be implemented similarly
}