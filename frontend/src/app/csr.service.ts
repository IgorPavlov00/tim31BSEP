import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Korisnik} from "./korisnik";
import {CSRRequest} from "./user/csrRequest";
import {CsrRequest} from "./CsrRequest";
import {Crt} from "./crt";


@Injectable({
  providedIn: 'root'
})
export class CsrService {

  private api='https://localhost:8084/bezbednost/radnik';
  constructor(private http:HttpClient) { }

  public getRequest():Observable<Crt[]>{
    return  this.http.get<Crt[]>(this.api+'/crt');
  }

  addRequest(request:CsrRequest): Observable<CsrRequest> {
    return this.http.post<any>(this.api+'/generateCSR', request);
  }
  // register(request: CSRRequest, file: any): Observable<any> {
  //   const formData = new FormData();
  //   formData.append("file", file);
  //   formData.append("request", new Blob([JSON.stringify(request)], {
  //     type: "application/json"
  //   }));
  //
  //   return this.http.post(environment.backUrl + "/users/register", formData,);
  // }
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

  getClients() {

  }

  revoke(id:number): Observable<Crt> {
    return this.http.post<any>(this.api+'/revoke', id);
  }
}
