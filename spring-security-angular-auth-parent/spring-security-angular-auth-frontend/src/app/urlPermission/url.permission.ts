import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class UrlPermission implements CanActivate {

  constructor(private router: Router) { }
  /**
   * 判斷是否能進入該頁
   * @param route
   * @param state
   */
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    //初始化回傳值
    let res = false;
    //如果使用者已登入 即可進入
    if (localStorage.getItem('currentUser')) {
      res = true;
    }

    // 如果未登入 導頁至登入頁
    else {
      this.router.navigate(['login'], { });
    }

    return res;
  }
}
