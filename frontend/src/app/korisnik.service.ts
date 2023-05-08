import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Korisnik} from './korisnik';
@Injectable({
  providedIn: 'root'
})
export class KorisnikService {
  private api='http://localhost:8084';
  constructor(private http:HttpClient) { }

  public getKorisnici():Observable<Korisnik[]>{
     return  this.http.get<Korisnik[]>(`${this.api}/all`);
  }
  public addKorisnik(radnik:Korisnik):Observable<Korisnik>{
    return  this.http.post<Korisnik>(`${this.api}/add`,radnik);
  }
  public updateKorisnik(radnik:Korisnik):Observable<Korisnik>{
    return  this.http.put<Korisnik>(`${this.api}/update`,radnik);
  }

  public deleteKorisnik(employeeId: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/delete/${employeeId}`);
  }
}
