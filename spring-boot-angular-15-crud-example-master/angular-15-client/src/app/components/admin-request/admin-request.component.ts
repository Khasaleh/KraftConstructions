import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-request',
  templateUrl: './admin-request.component.html',
  styleUrls: ['./admin-request.component.css']
})
export class AdminRequestComponent {
show = false;
ahide=true;
content() {
  this.show = true;
  this.ahide = false;

}
}
