import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgxJsonViewerModule } from 'ngx-json-viewer';
import { AuthoritieComponent } from './authoritie.component';

@NgModule({
  imports: [
    CommonModule,
    NgxJsonViewerModule
  ],
  declarations: [AuthoritieComponent]
})
export class AuthoritieModule { }
