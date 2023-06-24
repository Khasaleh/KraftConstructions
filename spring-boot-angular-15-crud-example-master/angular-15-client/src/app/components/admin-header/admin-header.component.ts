import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrls: ['./admin-header.component.css']
})

export class AdminHeaderComponent implements OnInit {
  user:any;
  globalUrl = 'http://99.72.32.144:8083';
ngOnInit(): void {
  this.user = JSON.parse(localStorage.getItem('user')!);

}
}
