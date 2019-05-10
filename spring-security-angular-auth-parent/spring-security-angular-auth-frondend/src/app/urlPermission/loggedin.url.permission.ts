import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class LoggedinUrlPermission implements CanActivate {

    constructor(private router: Router) { }
    /**
     * 判斷是否能進入登入頁面
     * @param route
     * @param state
     */
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

        //初始化回傳值
        let res = false;

        //如果使用者未登入 即可進入
        if (!localStorage.getItem('currentUser')) {
            res = true;
        }

        //如果已登入 導頁至第一頁
        else {
          this.router.navigate(['authoritie']);
        }

        return res;
    }
}
