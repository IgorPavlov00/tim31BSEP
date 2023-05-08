import { Component } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, NgForm, Validators} from "@angular/forms";
import { LoginService } from "../login.service";
import { Korisnik } from "../korisnik";
import { Router } from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {KorisnikService} from "../korisnik.service";
import { ValidatorFn } from '@angular/forms';

export function passwordValidator(): ValidatorFn {
  return (control: AbstractControl): {[key: string]: any} | null => {
    const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]).{12,}$/;
    const valid = passwordRegex.test(control.value);
    return valid ? null : { invalidPassword: true };
  };
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  id: number | undefined;
  ime: string | undefined;
  prezime: string | undefined;
  uloga: string | undefined;
  posao: string | undefined;
  email: string | undefined;

  korisnik:Korisnik=new Korisnik();

  constructor(
    private loginservice: LoginService,
    private router: Router,
    private rad: KorisnikService

  ) {
    this.korisnik.uloga="user";
  }
  onSubmit() {
    this.loginservice.loginUser(this.korisnik).subscribe(data=>{
      alert("login succesful!");
      const role=data.uloga;
      const id=this.korisnik.id;

      if(role.match('admin')){
        alert("Ulogovali ste se ko admin");
        this.router.navigate(['/admin']);
      }else  {
        alert("Ulogovali ste se ko user");
        this.router.navigate(['/user']);
      }
    },error => alert("sorry bad credentials!"));
  }
  // onSubmit() {
  //   this.loginservice.getUsers().subscribe(
  //     (data: any) => {
  //       console.log(data); // add this to check the data returned from the login service
  //       alert("login successful!");
  //
  //       const user1 = data.find((u: { ime: string; prezime: string; id: number; uloga: string; }) => u.ime === this.ime && u.prezime === this.prezime && u.id === this.id && u.uloga === 'admin');
  //       const user2 = data.find((u: { ime: string; prezime: string; id: number; uloga: string; }) => u.ime === this.ime && u.prezime === this.prezime && u.id === this.id && u.uloga === 'user');
  //       if (user1) {
  //         alert("Ulogovali ste se kao admin: " + user1.ime);
  //         this.router.navigate(['/admin']);
  //       } else if (user2) {
  //         alert("Ulogovali ste se kao user: " + user2.ime);
  //         this.router.navigate(['/user']);
  //       } else {
  //         alert("Sorry, bad credentials!");
  //       }
  //     },
  //     error => alert("Sorry, bad credentials!")
  //   );
  // }


  // addUser(addForm: NgForm) {
  //   // @ts-ignore
  //   addForm.value.uloga='user';
  //   const password = addForm.value.password;
  //   if (this.passwordPreventCommon(password)) {
  //     alert("Your password is too common. Please choose a different password.");
  //     return;
  //   }
  //   this.rad.addKorisnik(addForm.value).subscribe(
  //     (response: Korisnik) => {
  //       console.log(response);
  //       this.rad.getKorisnici();
  //       addForm.reset();
  //       alert("uspesno ste se registrovali!");
  //     },
  //     (error: HttpErrorResponse) => {
  //       alert(error.message);
  //       addForm.reset();
  //     }
  //   );
  //
  // }

  addUser(addForm: NgForm) {
    // @ts-ignore
    addForm.value.uloga='user';
    const password = this.korisnik.lozinka;
    console.log(password);
    if (this.passwordPreventCommon(password)) {
      alert("Your password is too common. Please choose a different password.");
      return;
    } else {
      this.rad.addKorisnik(addForm.value).subscribe(
        (response: Korisnik) => {
          console.log(response);
          this.rad.getKorisnici();
          addForm.reset();
          alert("uspesno ste se registrovali!");
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
          addForm.reset();
        }
      );
    }
  }

  passwordPreventCommon(password: string): boolean {
    const commonPasswords = [
      '123',
      'password',
      '12345678',
      // Add other commonly used passwords here
    ];

    if (commonPasswords.includes(password)) {
      alert("Your password is too common. Please choose a different password.");
      setTimeout(() => {
        console.log('Alert dismissed');
      }, 3000); // Delay execution of next line of code by 3 seconds
      return true;
    }

    return false;
  }


}
