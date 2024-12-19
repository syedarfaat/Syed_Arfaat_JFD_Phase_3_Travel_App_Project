import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { Booking } from '../booking';
import { Cab } from '../cab';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  bookings: Booking[] = [];
  cabs: Cab[] = [];
  newBooking: Booking = {
    user: { id: 0, username: '', password: '', role: '' }, cab: { id: 0, source: '', destination: '', type: '', kms: 0, cost: 0 }, dateTime: '', status: 'BOOKED', pickupLocation: '', dropoffLocation: '', fare: 0,
    id: 0
  };
  newCab: Cab = { id: 0, source: '', destination: '', type: '', kms: 0, cost: 0 };
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.loadBookings();
    this.loadCabs();
  }

  loadBookings() {
    this.apiService.getAllBookings().subscribe(
      (data) => {
        this.bookings = data;
      },
      (error) => {
        this.errorMessage = 'Error loading bookings';
      }
    );
  }

  loadCabs() {
    this.apiService.getAllCabsAdmin().subscribe(
      (data) => {
        this.cabs = data;
      },
      (error) => {
        this.errorMessage = 'Error loading cabs';
      }
    );
  }

  createBooking() {
    this.apiService.createBooking(this.newBooking).subscribe(
      (response) => {
        this.bookings.push(response);
        this.newBooking = { user: { id: 0, username: '', password: '', role: '' }, cab: { id: 0, source: '', destination: '', type: '', kms: 0, cost: 0 }, dateTime: '', status: 'BOOKED', pickupLocation: '', dropoffLocation: '', fare: 0, id:0 };
        this.successMessage = 'Booking created successfully';
        this.errorMessage = '';
      },
      (error) => {
        this.errorMessage = 'Error creating booking';
        this.successMessage = '';
      }
    );
  }

  updateBooking(booking: Booking) {
    this.apiService.updateBooking(booking.id, booking).subscribe(
      (response) => {
        // Update the booking in the list
        const index = this.bookings.findIndex(b => b.id === booking.id);
        this.bookings[index] = response;
        this.successMessage = 'Booking updated successfully';
        this.errorMessage = '';
      },
      (error) => {
        this.errorMessage = 'Error updating booking';
        this.successMessage = '';
      }
    );
  }

  deleteBooking(id: number) {
    this.apiService.deleteBooking(id).subscribe(
      () => {
        // Remove the booking from the list
        this.bookings = this.bookings.filter(b => b.id !== id);
        this.successMessage = 'Booking deleted successfully';
        this.errorMessage = '';
      },
      (error) => {
        this.errorMessage = 'Error deleting booking';
        this.successMessage = '';
      }
    );
  }

  createCab() {
    this.apiService.createCab(this.newCab).subscribe(
      (response) => {
        this.cabs.push(response);
        this.newCab = { id: 0, source: '', destination: '', type: '', kms: 0, cost: 0 };
        this.successMessage = 'Cab created successfully';
        this.errorMessage = '';
      },
      (error) => {
        this.errorMessage = 'Error creating cab';
        this.successMessage = '';
      }
    );
  }
  updateCab(cab: Cab) {
    this.apiService.updateCab(cab.id, cab).subscribe(
      (response) => {
        // Update the cab in the list
        const index = this.cabs.findIndex(c => c.id === cab.id);
        this.cabs[index] = response;
        this.successMessage = 'Cab updated successfully';
        this.errorMessage = '';
      },
      (error) => {
        this.errorMessage = 'Error updating cab';
        this.successMessage = '';
      }
    );
  }

  deleteCab(id: number) {
    this.apiService.deleteCab(id).subscribe(
      () => {
        // Remove the cab from the list
        this.cabs = this.cabs.filter(c => c.id !== id);
        this.successMessage = 'Cab deleted successfully';
        this.errorMessage = '';
      },
      (error) => {
        this.errorMessage = 'Error deleting cab';
        this.successMessage = '';
      }
    );
  }
}