import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrls: ['./admin-header.component.css']
})

export class AdminHeaderComponent implements OnInit {
  user:any;
  globalUrl = 'https://img.kraftconstructionco.com';
ngOnInit(): void {
  this.user = JSON.parse(localStorage.getItem('user')!);

}
}
