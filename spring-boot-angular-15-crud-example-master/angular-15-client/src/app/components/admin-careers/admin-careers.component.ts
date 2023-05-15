import { Component } from '@angular/core';
import { CareersComponent } from '../careers/careers.component';
@Component({
  selector: 'app-admin-careers',
  templateUrl: './admin-careers.component.html',
  styleUrls: ['./admin-careers.component.css']
})
export class AdminCareersComponent {
  show = false;
  ahide=true;
  content() {
    this.show = true;
    this.ahide = false;
  
  }
}
