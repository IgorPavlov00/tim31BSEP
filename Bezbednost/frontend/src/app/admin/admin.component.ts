import { Component } from '@angular/core';
import {Radnik} from "../radnik";
import {RadnikService} from "../radnik.service";
import {HttpErrorResponse} from "@angular/common/http";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  title = 'frontend';
  public radnici: Radnik[] | undefined;
  deleteEmployee: Radnik | undefined;
  editEmployee: Radnik | undefined;

  constructor(private rad: RadnikService) {
    this.ngOnInit();


  }

  ngOnInit() {
    this.getRadnici();

  }

  public getRadnici(): void {
    this.rad.getRadnici().subscribe((response: Radnik[]) => {
        this.radnici = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
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
    this.rad.addRadnik(addForm.value).subscribe(
      (response: Radnik) => {
        console.log(response);
        this.rad.getRadnici();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onDeleteEmloyee(employeeId: number): void {
    this.rad.deleteRadnik(employeeId).subscribe(
      (response: void) => {

        console.log(response);
        this.rad.getRadnici();
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);

      }
    );

  }

  public onUpdateEmloyee(employeeForm: NgForm): void {

    this.editEmployee=employeeForm.value;
    const r: Radnik = {
      id: this.editEmployee?.id,
      ime: employeeForm.value.name,
      prezime: employeeForm.value.email,
      posao: employeeForm.value.jobTitle,
      uloga:employeeForm.value.uloga.setValue("user")
    };

    this.rad.updateRadnik(r).subscribe(
      (response: Radnik) => {
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


  showForm2(r:Radnik) {
    document.getElementById('formElement').style.display = 'block';
    let popup=document.getElementById("popup2");
    popup.classList.add("open-popup");
    let form=document.getElementById("formElement2");
    this.editEmployee=r;

  }
}
