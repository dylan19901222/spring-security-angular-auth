import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { AuthoritieComponent } from './authoritie/authoritie.component';
import { LoginComponent } from './login/login.component';


import { UrlPermission } from "./urlPermission/url.permission";
import { LoggedinUrlPermission } from "./urlPermission/loggedin.url.permission";




const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full'},
  { path: 'authoritie', component: AuthoritieComponent, canActivate: [UrlPermission] },
  { path: 'login', component: LoginComponent, canActivate: [LoggedinUrlPermission]}
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  declarations: [],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
