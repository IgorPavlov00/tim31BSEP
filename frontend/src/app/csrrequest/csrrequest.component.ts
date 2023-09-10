import { Component } from '@angular/core';

@Component({
  selector: 'app-csrrequest',
  templateUrl: './csrrequest.component.html',
  styleUrls: ['./csrrequest.component.css']
})
export class CsrrequestComponent {
  displayedColumns = ['email', 'timestamp', 'firstName', 'lastName'];
  csrRequests: any;
}
