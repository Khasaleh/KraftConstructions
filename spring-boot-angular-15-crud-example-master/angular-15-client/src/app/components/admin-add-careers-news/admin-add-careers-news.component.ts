import { Component } from '@angular/core';
// import { ColorPickerControl } from 'ngx-color-picker';

@Component({
  selector: 'app-admin-add-careers-news',
  templateUrl: './admin-add-careers-news.component.html',
  styleUrls: ['./admin-add-careers-news.component.css']
})
export class AdminAddCareersNewsComponent {
  selectedColor: string = '';
  textColor: string = '';
  isCardBodyVisible: boolean = false;
  toggleCardBody() {
    this.isCardBodyVisible = !this.isCardBodyVisible;
  }
  

}
