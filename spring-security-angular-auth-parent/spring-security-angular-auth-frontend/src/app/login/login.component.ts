import { Component, OnInit } from '@angular/core';
import { User } from "../model/model.user";
import { AuthService } from "../services/auth.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User = new User();
  errorMessage: string;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
  }

  /**
   * 登入
   */
  login() {
    this.authService.logIn(this.user)
      .subscribe(data => {
        this.router.navigate(['authoritie']);
      }, err => {
        this.errorMessage = "error :  Username or password is incorrect";
      }
      )
  }
}
