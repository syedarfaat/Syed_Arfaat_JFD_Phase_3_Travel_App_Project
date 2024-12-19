import { Component } from '@angular/core';
import { ApiService } from '../api.service';
import { User } from '../user';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent {
  user: User = { id: 0, username: '', password: '', role: 'USER' };
  error: string = '';

  constructor(private apiService: ApiService) { }

  registerUser() {
    this.apiService.createUser(this.user).subscribe(
      (response) => {
        // Save the user's authentication information (e.g., token, userId)
        localStorage.setItem('userId', response.id!.toString());
        localStorage.setItem('userRole', response.role);
        // Navigate to the user dashboard or show a success message
      },
      (error) => {
        this.error = 'Error registering user';
      }
    );
  }
}