package com.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
  
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key to User
    private User user; // Reference to the User who made the booking // Reference to the User who made the booking
    
    
    @ManyToOne
    @JoinColumn(name = "cab_id", nullable = false) // Foreign key to Cab
    private Cab cab;// Reference to the Cab being booked
    private String dateTime; // Date and time of the booking
    private String status; // e.g., PENDING, CONFIRMED
    private String pickupLocation; // Pickup location
    private String dropoffLocation; // Drop-off location
    private double fare; // Total fare for the booking
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPickupLocation() {
		return pickupLocation;
	}
	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}
	public String getDropoffLocation() {
		return dropoffLocation;
	}
	public void setDropoffLocation(String dropoffLocation) {
		this.dropoffLocation = dropoffLocation;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public Cab getCab() {
		return cab;
	}
	public void setCab(Cab cab) {
		this.cab = cab;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Booking(Long id, User user, Cab cab, String dateTime, String status, String pickupLocation,
			String dropoffLocation, double fare) {
		super();
		this.id = id;
		this.user = user;
		this.cab = cab;
		this.dateTime = dateTime;
		this.status = status;
		this.pickupLocation = pickupLocation;
		this.dropoffLocation = dropoffLocation;
		this.fare = fare;
	}

    
    
}