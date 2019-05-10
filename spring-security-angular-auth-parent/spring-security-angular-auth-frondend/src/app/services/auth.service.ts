import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { User } from "../model/model.user";
import 'rxjs/add/operator/map';

@Injectable()
export class AuthService {

  constructor(public http: Http) { }

  /**
   * 登入
   * @param user
   */
  public logIn(user: User) {
    //將各項參數 初始化並封裝
    let headers = new Headers();
    headers.append('Accept', 'application/json')
    var base64Credential: string = btoa(user.username + ':' + user.password);
    headers.append("Authorization", "Basic " + base64Credential);
    let options = new RequestOptions();
    options.headers = headers;

    //進行登入驗證
    return this.http.get("api/account/login", options)
      .map((response: Response) => {
        console.log(response.json())
        let user = response.json();

        if (user) {
          //賦予登入成功的user localStorage及API的驗證權杖
          localStorage.setItem('currentUser', JSON.stringify(user));
          localStorage.setItem('token', base64Credential);
        }
      });
  }

  logOut() {
    // 移除 localStorage及API的驗證權杖
    return this.http.post("api/account/logout", {})
      .map((response: Response) => {
        localStorage.removeItem('currentUser');
        localStorage.removeItem('token');
      });
  }

}
