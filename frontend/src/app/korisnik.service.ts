import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Korisnik} from './korisnik';
import {CSRRequest} from "./user/csrRequest";
@Injectable({
  providedIn: 'root'
})
export class KorisnikService {
  private api='https://localhost:8084';
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
  register(request: CSRRequest, file: any): Observable<any> {
    const formData = new FormData();
    formData.append("file", file);
    formData.append("request", new Blob([JSON.stringify(request)], {
      type: "application/json"
    }));

    return this.http.post(`${this.api}/add`, formData,);
  }
}
