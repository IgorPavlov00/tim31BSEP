import { Component } from '@angular/core';
import {CsrService} from "../csr.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Crt} from "../crt";
import {CsrRequest} from "../CsrRequest";

@Component({
  selector: 'app-show-csrdata',
  templateUrl: './show-csrdata.component.html',
  styleUrls: ['./show-csrdata.component.css']
})
export class ShowCSRDataComponent {
  public data: Crt[];
  constructor(private crt:CsrService ) {


  }
  ngOnInit(){
    this.crt.getRequest().subscribe(
      (data) => {
        this.setData(data);
      },
      (error: HttpErrorResponse) => {
        console.log('Error occurred while fetching clients:', error);
      }
    )
  }

  private setData(data: Crt[]) {
     this.data=data;
  }

  revoke(row:Crt) {
    console.log(row)
    this.crt.revoke(row.id).subscribe(
      () => {
        row.valid=false;
      },
      (error: HttpErrorResponse) => {
        console.log('Error occurred while fetching clients:', error);
      }
    )

  }
}
