import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { LoginService } from "../login.service";
import { Radnik } from "../radnik";
import { Router } from "@angular/router";

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

  radnik:Radnik=new Radnik();

  constructor(
    private loginservice: LoginService,
    private router: Router
  ) {}
  onSubmit() {
    this.loginservice.loginUser(this.radnik).subscribe(data=>{
      alert("login succesful!");
      const role=this.radnik.uloga;
      const id=this.radnik.id;

      if(role.match('admin')){
        alert("Ulogovali ste se ko admin");
        this.router.navigate(['/admin']);
      }else  {
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
}
