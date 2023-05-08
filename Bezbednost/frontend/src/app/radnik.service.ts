import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Radnik} from './radnik';
@Injectable({
  providedIn: 'root'
})
export class RadnikService {
  private api='http://localhost:8084';
  constructor(private http:HttpClient) { }

  public getRadnici():Observable<Radnik[]>{
     return  this.http.get<Radnik[]>(`${this.api}/all`);
  }
  public addRadnik(radnik:Radnik):Observable<Radnik>{
    return  this.http.post<Radnik>(`${this.api}/add`,radnik);
  }
  public updateRadnik(radnik:Radnik):Observable<Radnik>{
    return  this.http.put<Radnik>(`${this.api}/update`,radnik);
  }

  public deleteRadnik(employeeId: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/delete/${employeeId}`);
  }
}
