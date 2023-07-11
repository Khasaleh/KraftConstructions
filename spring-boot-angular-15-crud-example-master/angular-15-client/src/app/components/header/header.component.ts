import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { data } from 'jquery';
import { HomeServiceService } from 'src/app/service/home-service.service';
import { InteriorRemodelingService } from 'src/app/service/interior-remodeling.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css', '../../../styles.css']
})
export class HeaderComponent implements OnInit {
  sliderData: any;
  DropData: any
  DropData2: any
  serviceData: any
  imageData: any[] =[];
  constructor(private headerService: HomeServiceService, private intService: InteriorRemodelingService, private router: Router) { }
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
    this.headerService.getServiceByPage1().subscribe(
      response => {
        this.DropData = response;
        console.log(this.DropData);
      }
    )
    this.headerService.getServiceByPage2().subscribe(
      response => {
        this.DropData2 = response;
      }
    )

  }

  getOneService(serviceId: any) {
    this.intService.updateData(serviceId);
    this.router.navigate(['/interior-remodelling']);
  }
  getOneService1(serviceId: any) {
    this.intService.updateData(serviceId);
    this.router.navigate(['/new-additions']);
  }

  getImages(service: any) {
    this.intService.getServicesImages(service).subscribe( 
      response => {
        this.imageData = response;
        console.log(this.imageData);
      },
      error => {
        console.log(error);
      }
    )
  }
}



