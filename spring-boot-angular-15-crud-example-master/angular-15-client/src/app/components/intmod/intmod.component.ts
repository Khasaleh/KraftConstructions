import { Component } from '@angular/core';
import { error } from 'jquery';
import { InteriorRemodelingService } from 'src/app/service/interior-remodeling.service';

@Component({
  selector: 'app-intmod',
  templateUrl: './intmod.component.html',
  styleUrls: ['./intmod.component.css']
})
export class IntmodComponent {
  serviceData: any
  imageData:any
  data: any[] = [];
  globalUrl = 'http://99.72.32.144:8083'

  constructor(private interiorRemodService: InteriorRemodelingService) { }
  ngOnInit() {
    // this.getDataonload();
    this.interiorRemodService.getServiceByPage1().subscribe(
      response => {
       
        this.data = response.filter((service: { active: boolean; }) => service.active === true);
        console.log(this.data);
    console.log(this.data[0].id);
    this.getOneService(this.data[0].id);
      },
      error => {
        console.log(error);

      }
    )
  }

  getOneService(service: any){
    this.interiorRemodService.getServiceById(service).subscribe(
      response =>{
        this.serviceData = response;
        console.log(this.serviceData);
      },
      error => {
        console.log(error);
        
      }
    )
    this.getImages(service);
  }
  getImages(service: any){
    this.interiorRemodService.getServicesImages(service).subscribe(
      response =>{
        this.imageData = response;
        console.log(this.imageData);
      },
      error => {
        console.log(error);
        
      }
    )
  }
}
