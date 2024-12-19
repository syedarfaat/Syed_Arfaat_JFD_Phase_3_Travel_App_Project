import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user'; // Create a User model
import { Booking } from './booking'; // Create a Booking model
import { Cab } from './cab'; // Create a Cab model

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = 'http://localhost:8080/api/users'; // Your Spring Boot API URL
  private adminUrl = 'http://localhost:8080/api/admin'; // Admin API URL

  constructor(private http: HttpClient) { }


  login(user: User): Observable<{ id: number, username: string, role: string }> {
    return this.http.post<{ id: number, username: string, role: string }>(`${this.baseUrl}/login`, user);
  }


  createUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/users`, user);
  }

  createBooking(booking: Booking): Observable<Booking> {
    return this.http.post<Booking>(`${this.baseUrl}/bookings`, booking);
  }

  getAllCabs(): Observable<Cab[]> {
    return this.http.get<Cab[]>(`${this.baseUrl}/cabs`);
  }

  // Admin operations
  getAllBookings(): Observable<Booking[]> {
    return this.http.get<Booking[]>(`${this.adminUrl}/bookings`);
  }

  getBookingById(id: number): Observable<Booking> {
    return this.http.get<Booking>(`${this.adminUrl}/bookings/${id}`);
  }

  updateBooking(id: number, booking: Booking): Observable<Booking> {
    return this.http.put<Booking>(`${this.adminUrl}/bookings/${id}`, booking);
  }

  deleteBooking(id: number): Observable<void> {
    return this.http.delete<void>(`${this.adminUrl}/bookings/${id}`);
  }

  createCab(cab: Cab): Observable<Cab> {
    return this.http.post<Cab>(`${this.adminUrl}/cabs`, cab);
  }

  getAllCabsAdmin(): Observable<Cab[]> {
    return this.http.get<Cab[]>(`${this.adminUrl}/cabs`);
  }

  updateCab(id: number, cab: Cab): Observable<Cab> {
    return this.http.put<Cab>(`${this.adminUrl}/cabs/${id}`, cab);
  }

  deleteCab(id: number): Observable<void> {
    return this.http.delete<void>(`${this.adminUrl}/cabs/${id}`);
  }
}