package com.project.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String source;
    private String destination;
    private String type; // e.g., Sedan, SUV
    private double kms; // Distance in kilometers
    private double cost; // Cost per kilometer

    
    @OneToMany(mappedBy = "cab")
    private List<Booking> bookings;// Total fare for the booking

    public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	// Additional methods can be added to calculate fare based on distance
    public double calculateFare() {
        return kms * cost; // Example calculation
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getKms() {
		return kms;
	}

	public void setKms(double kms) {
		this.kms = kms;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}




	public Cab() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cab(Long id, String source, String destination, String type, double kms, double cost, 
			List<Booking> bookings) {
		super();
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.type = type;
		this.kms = kms;
		this.cost = cost;

		this.bookings = bookings;
	}
}