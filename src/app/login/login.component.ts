import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../api.service';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user: User = { username: '', password: '', role: '' };
  error: string = '';

  constructor(private apiService: ApiService, private router: Router) { }

  login() {
    this.apiService.login(this.user).subscribe(
      (response) => {
        // Save the user information in the local storage
      
        localStorage.setItem('username', response.username);
        localStorage.setItem('role', response.role);

        // Navigate to the appropriate dashboard based on the user's role
        if (response.role === 'ADMIN') {
          this.router.navigate(['/admin-dashboard']);
        } else {
          this.router.navigate(['/user-dashboard']);
        }
      },
      (error) => {
        this.error = 'Invalid username or password';
      }
    );
  }
}