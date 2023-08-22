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
  imageData: any[] = [];
  data: any[] = [];
  globalUrl = 'https://img.kraftconstructionco.com';
  itemId: any;
  selectedServiceId: any

  constructor(private interiorRemodService: InteriorRemodelingService) { }
  ngOnInit() {
    this.interiorRemodService.data$.subscribe(
      response => {
        this.itemId = response;
      }
    );
    this.interiorRemodService.getServiceByPage1().subscribe(
      response => {
        this.data = response.filter((service: { active: boolean; }) => service.active === true);
        if (this.itemId) {
          this.getOneService(this.itemId);
        }
        else {
          this.getOneService(this.data[0].id);
        }
      }
    )
  }
  getOneService(service: any) {
    this.selectedServiceId = service;
    this.interiorRemodService.getServiceById(service).subscribe(
      response => {
        this.serviceData = response;
      }
    )
    this.getImages(service);
  }
  getImages(service: any) {
    this.interiorRemodService.getServicesImages(service).subscribe(
      response => {
        this.imageData = response;
      }
    )
  }
}
