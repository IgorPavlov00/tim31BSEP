import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Korisnik} from "./korisnik";


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiUrl = 'http://localhost:8084';

  constructor(private http: HttpClient) {}

  public loginUser(radnik: Korisnik): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, radnik);
  }

  public getUsers(): Observable<Korisnik[]> {
    return this.http.get<Korisnik[]>(`${this.apiUrl}/svi`);
  }
}
