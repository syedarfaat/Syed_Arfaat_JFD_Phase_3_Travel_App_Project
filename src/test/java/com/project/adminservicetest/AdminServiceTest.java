package com.project.adminservicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.model.User;
import com.project.repository.UserRepository;
import com.project.service.AdminService;
import com.project.service.BookingService;
import com.project.service.CabService;

class AdminServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookingService bookingService;

    @Mock
    private CabService cabService;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        // This method will run after each test method
        System.out.println("Test case completed.");
    }

    @Test
    void testCreateUser() {
        User mockUser = new User(1L, "john_doe", "password123", "USER");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        User createdUser = adminService.createUser(mockUser);
        assertNotNull(createdUser);
        assertEquals("john_doe", createdUser.getUsername());
        verify(userRepository, times(1)).save(mockUser);
    }

    @Test
    void testUpdateUser() {
        User mockUser = new User(1L, "john_doe", "password123", "USER");
        User updatedDetails = new User(1L, "jane_doe", "password456", "ADMIN");

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedDetails);

        User updatedUser = adminService.updateUser(1L, updatedDetails);
        assertNotNull(updatedUser);
        assertEquals("jane_doe", updatedUser.getUsername());
        assertEquals("password456", updatedUser.getPassword());
        assertEquals("ADMIN", updatedUser.getRole());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        adminService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetUserById() {
        User mockUser = new User(1L, "john_doe", "password123", "USER");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        User user = adminService.getUserById(1L);
        assertNotNull(user);
        assertEquals("john_doe", user.getUsername());
    }

    @Test
    void testGetUserByIdNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        User user = adminService.getUserById(1L);
        assertNull(user);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User(1L, "john_doe", "password123", "USER");
        User user2 = new User(2L, "jane_doe", "password456", "ADMIN");
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = adminService.getAllUsers();
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }
}
