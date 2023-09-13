import { Component } from '@angular/core';
import {FormBuilder, NgForm} from "@angular/forms";
import {Korisnik} from "../korisnik";
import {HttpErrorResponse} from "@angular/common/http";
import {KorisnikService} from "../korisnik.service";
import {CsrService} from "../csr.service";
import {CSRRequest} from "../user/csrRequest";
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {CsrRequest} from "../CsrRequest";
@Component({
  selector: 'app-csrrequest',
  templateUrl: './csrrequest.component.html',
  styleUrls: ['./csrrequest.component.css']
})
export class CsrrequestComponent {


  public formData: CsrRequest = new CsrRequest();
  constructor(private CSR: CsrService,private fb:FormBuilder) {

  }

  AddRequest() {
    console.log(this.formData);
    this.CSR.addRequest(this.formData).subscribe(
      (response) => {
        console.log('Form data sent successfully:', response);

        // Handle the response from the backend if needed
      },
      (error) => {
        console.error('Error sending form data:', error);
        // Handle errors if any
      }
    );
  }
}
