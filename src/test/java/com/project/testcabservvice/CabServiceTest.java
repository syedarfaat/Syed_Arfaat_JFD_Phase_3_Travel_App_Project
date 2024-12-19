package com.project.testcabservvice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

import com.project.model.Cab;
import com.project.repository.CabRepository;
import com.project.service.CabService;

class CabServiceTest {

    @Mock
    private CabRepository cabRepository;

    @InjectMocks
    private CabService cabService;

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
    void testCreateCab() {
        Cab mockCab = new Cab();
        mockCab.setId(1L);
        when(cabRepository.save(any(Cab.class))).thenReturn(mockCab);

        Cab createdCab = cabService.createCab(mockCab);
        assertNotNull(createdCab);
        assertEquals(1L, createdCab.getId());
        verify(cabRepository, times(1)).save(mockCab);
    }

    @Test
    void testGetAllCabs() {
        Cab cab1 = new Cab();
        cab1.setId(1L);
        Cab cab2 = new Cab();
        cab2.setId(2L);
        when(cabRepository.findAll()).thenReturn(Arrays.asList(cab1, cab2));

        List<Cab> cabs = cabService.getAllCabs();
        assertEquals(2, cabs.size());
        verify(cabRepository, times(1)).findAll();
    }

    @Test
    void testGetCabById() {
        Cab mockCab = new Cab();
        mockCab.setId(1L);
        when(cabRepository.findById(1L)).thenReturn(Optional.of(mockCab));

        Cab cab = cabService.getCabById(1L);
        assertNotNull(cab);
        assertEquals(1L, cab.getId());
    }

    @Test
    void testGetCabByIdNotFound() {
        when(cabRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            cabService.getCabById(1L);
        });

        assertEquals("Cab not found with id: 1", exception.getMessage());
    }

    @Test
    void testUpdateCab() {
        Cab mockCab = new Cab();
        mockCab.setId(1L);
        Cab updatedDetails = new Cab();
        updatedDetails.setSource("New Source");
        updatedDetails.setDestination("New Destination");
        updatedDetails.setType("New Type");
        updatedDetails.setKms(100.0);
        updatedDetails.setCost(50.0);

        when(cabRepository.findById(1L)).thenReturn(Optional.of(mockCab));
        when(cabRepository.save(any(Cab.class))).thenReturn(mockCab);

        Cab updatedCab = cabService.updateCab(1L, updatedDetails);
        assertEquals("New Source", updatedCab.getSource());
        assertEquals("New Destination", updatedCab.getDestination());
        assertEquals("New Type", updatedCab.getType());
        assertEquals(100.0, updatedCab.getKms());
        assertEquals(50.0, updatedCab.getCost());
    }

    @Test
    void testDeleteCab() {
        when(cabRepository.existsById(1L)).thenReturn(true);

        cabService.deleteCab(1L);

        verify(cabRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteCabNotFound() {
        when(cabRepository.existsById(anyLong())).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            cabService.deleteCab(1L);
        });

        assertEquals("Cab not found with id: 1", exception.getMessage());
    }
}