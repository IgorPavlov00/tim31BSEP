import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Radnik } from './radnik';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiUrl = 'http://localhost:8084';

  constructor(private http: HttpClient) {}

  public loginUser(radnik: Radnik): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, radnik);
  }

  public getUsers(): Observable<Radnik[]> {
    return this.http.get<Radnik[]>(`${this.apiUrl}/svi`);
  }
}
