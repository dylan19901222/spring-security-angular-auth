import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  providers: [],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  public isUserLoggedIn: boolean;

  constructor() {
  }

  ngOnInit(): void {

  }

  isLoggedIn(): boolean {

    if (localStorage.getItem('currentUser')) {
      // logged in so return true
      return true;
    }
    return false;
  }
}


