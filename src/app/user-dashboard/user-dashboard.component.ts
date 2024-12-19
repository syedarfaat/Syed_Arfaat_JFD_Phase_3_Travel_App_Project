import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../api.service';
import { Booking } from '../booking';
import { Cab } from '../cab';
import { User } from '../user';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {
deleteBooking(arg0: number) {
throw new Error('Method not implemented.');
}
  bookings: Booking[] = [];
  cabs: Cab[] = [];
  newBooking: Booking = { user: { id: 0, username: '', password: '', role: '' }, cab: { id: 0, source: '', destination: '', type: '', kms: 0, cost: 0 }, dateTime: '', status: 'BOOKED', pickupLocation: '', dropoffLocation: '', fare: 0, id:0 };
  user: User = { id: 0, username: '', password: '', role: '' };
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit() {
    this.loadUserBookings();
    this.loadCabs();
  
  }

  loadUserBookings() {
    
    this.apiService.getAllBookings().subscribe(
      data => {
        this.bookings = data;
      },
      error => {
        this.errorMessage = 'Error loading bookings. Please try again later.';
      }
    );
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
    if (this.user.id !== undefined) {
    this.newBooking.user = this.user as { id: number; username: string; password: string; role: string };;
        this.apiService.createBooking(this.newBooking).subscribe(
            (response) => {
                this.bookings.push(response);
                this.newBooking = { id:0, user: { id: 0, username: '', password: '', role: '' }, cab: { id: 0, source: '', destination: '', type: '', kms: 0, cost: 0 }, dateTime: '', status: 'BOOKED', pickupLocation: '', dropoffLocation: '', fare: 0 };
                this.successMessage = 'Booking created successfully!';
                this.errorMessage = '';
                this.router.navigate(['/user-dashboard']);
            },
            (error) => {
                this.errorMessage = 'Error creating booking. Please try again later.';
                this.successMessage = '';
            }
        );
    } else {
        this.errorMessage = 'User ID is not defined. Please log in again.';
    }
}

  cancelBooking(id: number) {
    this.apiService.deleteBooking(id).subscribe(
      () => {
        // Remove the cancelled booking from the list
        this.bookings = this.bookings.filter(b => b.id !== id);
        this.successMessage = 'Booking cancelled successfully!';
        this.errorMessage = '';
        this.router.navigate(['/user-dashboard']);
      },
      (error) => {
        this.errorMessage = 'Error cancelling booking. Please try again later.';
        this.successMessage = '';
      }
    );
  }
}