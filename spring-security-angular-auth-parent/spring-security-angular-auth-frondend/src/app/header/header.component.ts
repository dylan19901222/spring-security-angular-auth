import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { AuthService } from "../services/auth.service";
import { Router } from "@angular/router";


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {

  }

  logout() {
    this.authService.logOut()
      .subscribe(data => {
        localStorage.removeItem("currentUser")
        this.router.navigate(['/login']);
      }, err => {
        console.log("error");
      }
      );
  }

  // 檢查localstorage是否有currentUser，判斷是否登入
  isLogin() {
    if (localStorage.getItem("currentUser")) {
      return true;
    }
    else
      return false;
  }

  getUserName(){
    let username=''
    if (localStorage.getItem("currentUser")) {
      let currentUser = JSON.parse(localStorage.getItem("currentUser"))
      username = currentUser.username
    }
    else {
      username = ''
    }
    return username
  }

}
