import { Component, OnInit } from '@angular/core';
import { HomeServiceService } from 'src/app/service/home-service.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css', '../../../styles.css']
})
export class HeaderComponent implements OnInit {
  sliderData: any;
  constructor(private headerService: HomeServiceService) {}
  ngOnInit(): void {
    this.headerService.getsliderdata().subscribe(
      data => {
        this.sliderData = data;
        console.log("headerData", data);
      },
      error => {
        console.log("Error:", error);
      }
    );
  }
}
