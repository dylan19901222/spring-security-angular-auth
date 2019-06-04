import { Injectable } from '@angular/core';
import { HeaderService } from './header.service';
import { Http, RequestOptions } from '@angular/http';

@Injectable()
export class AuthoritieTestService {

  constructor(private http: Http, private headerService: HeaderService) { }


  findAll() {
    let requestOptions = new RequestOptions({ headers: this.headerService.getHeader() });
    return this.http.get('api/findAll', requestOptions)
  }

  findSelf() {
    let requestOptions = new RequestOptions({ headers: this.headerService.getHeader() });
    return this.http.get('api/findSelf', requestOptions)
  }

  findUserName() {
    let requestOptions = new RequestOptions({ headers: this.headerService.getHeader() });
    return this.http.get('api/findUserName', requestOptions)
  }


}
