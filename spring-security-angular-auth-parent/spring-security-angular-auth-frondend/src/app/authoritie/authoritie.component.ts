import { Component, OnInit } from '@angular/core';
import { AuthoritieTestService } from '../services/authoritie-test.service';

@Component({
  selector: 'app-authoritie',
  templateUrl: './authoritie.component.html',
  styleUrls: ['./authoritie.component.css']
})
export class AuthoritieComponent implements OnInit {

  jsonObject={}
  error = ''

  constructor(private authoritieTestService: AuthoritieTestService) { }

  ngOnInit() {

  }

  findAll() {
    this.authoritieTestService.findAll()
      .subscribe(data => {
          this.error = ''
          this.jsonObject = data
        }, err => {
          this.jsonObject={}
          this.error= err
        }
      )
  }

  findSelf() {
    this.authoritieTestService.findSelf()
      .subscribe(data => {
          this.error = ''
          this.jsonObject = data
        }, err => {
          this.jsonObject = {}
          this.error = err
        }
      )
  }

  findUserName() {
    this.authoritieTestService.findUserName()
      .subscribe(data => {
          this.error = ''
          this.jsonObject = data
        }, err => {
          this.jsonObject = {}
          this.error = err
        }
      )
  }

}
