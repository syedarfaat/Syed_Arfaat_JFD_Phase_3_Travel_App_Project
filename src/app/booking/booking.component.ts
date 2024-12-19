import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../api.service';
import { Booking } from '../booking';
import { User } from '../user';
import { Cab } from '../cab';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {
  booking: Booking = {
    id: 0,
    user: { id: 0, username: '', password: '', role: '' },
    cab: { id: 0, source: '', destination: '', type: '', kms: 0, cost: 0 },
    dateTime: '',
    status: 'BOOKED',
    pickupLocation: '',
    dropoffLocation: '',
    fare: 0
  };
  cabs: Cab[] = [];
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit() {
    this.loadCabs();
  }

  loadCabs() {
    this.apiService.getAllCabs().subscribe(
      data => {
        this.cabs = data;
      },
      error => {
        this.errorMessage = 'Error loading cabs. Please try again later.';
      }
    );
  }

  createBooking() {
    this.booking.user.id = parseInt(localStorage.getItem('userId') || '0');
    this.apiService.createBooking(this.booking).subscribe(
      (response) => {
        // Reset the booking form
        this.booking = {
          id: 0,
          user: { id: 0, username: '', password: '', role: '' },
          cab: { id: 0, source: '', destination: '', type: '', kms: 0, cost: 0 },
          dateTime: '',
          status: 'BOOKED',
          pickupLocation: '',
          dropoffLocation: '',
          fare: 0
        };
        this.successMessage = 'Booking created successfully!';
        this.errorMessage = '';
        this.router.navigate(['/admin-dashboard']);
      },
      (error) => {
        this.errorMessage = 'Error creating booking. Please try again later.';
        this.successMessage = '';
      }
    );
  }
}