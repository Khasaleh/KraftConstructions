import { Component } from '@angular/core';
import { InteriorRemodelingService } from 'src/app/service/interior-remodeling.service';

@Component({
  selector: 'app-new-add',
  templateUrl: './new-add.component.html',
  styleUrls: ['./new-add.component.css'
  ]
})
export class NewAddComponent {
  serviceData: any
  imageData: any
  data!: any[];
  globalUrl = 'https://img.kraftconstructionco.com';
  itemId: any;
  selectedServiceId: any

  constructor(private newAdditionService: InteriorRemodelingService) { }
  ngOnInit() {
    this.newAdditionService.data$.subscribe(
      response => {
        this.itemId = response;

      }
    );
    this.newAdditionService.getServiceByPage2().subscribe(
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
    this.newAdditionService.getServiceById(service).subscribe(
      response => {
        if (response) {
          this.serviceData = response;
        }
      },
      error =>{
        if(error){
          this.serviceData = null;
        }
      }
    )
    this.getImages(service)
  }
  getImages(service: any) {
    this.newAdditionService.getServicesImages(service).subscribe(
      response => {
        this.imageData = response;
      }
    )
  }
}
