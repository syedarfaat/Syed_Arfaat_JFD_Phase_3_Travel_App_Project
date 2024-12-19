import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';
import { BookingComponent } from './booking/booking.component';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminDashboardComponent,
    UserDashboardComponent,
    UserRegistrationComponent,
    BookingComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule, // For making HTTP requests
    FormsModule, // For handling forms
    AppRoutingModule // For routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }