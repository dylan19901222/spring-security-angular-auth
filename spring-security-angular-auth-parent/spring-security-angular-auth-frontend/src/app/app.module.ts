import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppRoutingModule } from './/app-routing.module';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { LoginModule } from './login/login.module';
import { AuthoritieModule } from './authoritie/authoritie.module';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';

import { AuthService } from "./services/auth.service";
import { HeaderService } from './services/header.service';

import { UrlPermission } from "./urlPermission/url.permission";
import { LoggedinUrlPermission } from "./urlPermission/loggedin.url.permission";
import { AuthoritieTestService } from './services/authoritie-test.service';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    LoginModule,
    AuthoritieModule
  ],
  providers: [AuthService, UrlPermission, LoggedinUrlPermission, HeaderService, AuthoritieTestService],
  bootstrap: [AppComponent]
})
export class AppModule { }
