import {Component, ViewChild} from '@angular/core';

import {KorisnikService} from "../korisnik.service";
import {HttpErrorResponse} from "@angular/common/http";
import {NgForm} from "@angular/forms";
import {Korisnik} from "../korisnik";


import { Validators } from '@angular/forms';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {

  @ViewChild('addForm', { static: true }) addForm!: NgForm;
  title = 'frontend';
  public radnici: Korisnik[] | undefined;
  deleteEmployee: Korisnik | undefined;
  editEmployee: Korisnik | undefined;

  constructor(private rad: KorisnikService) {
    this.ngOnInit();


  }

  ngOnInit() {
    this.getRadnici();

  }

  public getRadnici(): void {
    this.rad.getKorisnici().subscribe((response: Korisnik[]) => {
      let filteredRadnici: Korisnik[] = [];
      for (let r of response) {
        if (r.uloga === "user") {
          filteredRadnici.push(r);
        }
      }
      this.radnici = filteredRadnici;
      console.log(this.radnici);
    }, (error: HttpErrorResponse) => {
      alert(error.message);
    });
  }


  showForm() {
    // @ts-ignore
    document.getElementById('formElement').style.display = 'block';
    let popup=document.getElementById("popup");
    popup.classList.add("open-popup");

  }

  public onAddEmloyee(addForm: NgForm): void {
    // @ts-ignore
    document.getElementById('add-employee-form').click();
    this.rad.addKorisnik(addForm.value).subscribe(
      (response: Korisnik) => {
        console.log(response);
        this.rad.getKorisnici();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onDeleteEmloyee(employeeId: number): void {
    this.rad.deleteKorisnik(employeeId).subscribe(
      (response: void) => {

        console.log(response);
        this.rad.getKorisnici();
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);

      }
    );

  }

  public onUpdateEmloyee(employeeForm: NgForm): void {

    this.editEmployee=employeeForm.value;
    const r: Korisnik = {
      id: this.editEmployee?.id,
      ime: employeeForm.value.name,
      prezime: employeeForm.value.email,
      posao: employeeForm.value.jobTitle,
      uloga:employeeForm.value.uloga.setValue("user"),
      email:employeeForm.value.email,
      lozinka:employeeForm.value.lozinka
    };

    this.rad.updateKorisnik(r).subscribe(
      (response: Korisnik) => {
        console.log(response);
        this.getRadnici();
        this.closeForm2();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }





  closeForm() {
    let popup=document.getElementById("popup");
    popup.classList.remove("open-popup");
    window.location.reload();
  }
  closeForm2() {
    let popup=document.getElementById("popup2");
    popup.classList.remove("open-popup");
    window.location.reload();
  }


  showForm2(r:Korisnik) {
    document.getElementById('formElement').style.display = 'block';
    let popup=document.getElementById("popup2");
    popup.classList.add("open-popup");
    let form=document.getElementById("formElement2");
    this.editEmployee=r;

  }
}
