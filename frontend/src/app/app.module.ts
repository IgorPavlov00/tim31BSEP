import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';

import {KorisnikService} from "./korisnik.service";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import { LoginComponent } from './login/login.component';
import {RouterModule, Routes} from "@angular/router";
import { AdminComponent } from './admin/admin.component';
import { UserComponent } from './user/user.component';
import { CsrrequestComponent } from './csrrequest/csrrequest.component';
const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'user', component: UserComponent },
  { path: 'csrrequests', component: CsrrequestComponent },

];
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminComponent,
    UserComponent,
    CsrrequestComponent

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    RouterModule.forRoot(routes),
    BrowserModule

  ],
  providers: [KorisnikService],
  bootstrap: [AppComponent]
})
export class AppModule { }
