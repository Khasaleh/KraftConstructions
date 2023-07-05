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
  globalUrl = 'http://99.72.32.144:8083'
  constructor(private newAdditionService: InteriorRemodelingService) { }
  ngOnInit() {
    this.newAdditionService.getServicebyPage().subscribe(
      response => {
        this.serviceData = response;
        console.log(response);
      }
    )
    this.newAdditionService.getServiceByPage2().subscribe(
      response => {
        this.data = response.filter((service: { active: boolean; }) => service.active === true);

        console.log(this.data);
        this.getOneService(this.data[0].id);
        
      },
      error => {
        console.log(error);

      }
    )
  }
 
  getOneService(service: any){
    this.newAdditionService.getServiceById(service).subscribe(
      response =>{
        this.serviceData = response;
        console.log(this.serviceData);
      },
      error => {
        console.log(error);
        
      }
    )
    this.getImages(service)
  }
  getImages(service: any){
    this.newAdditionService.getServicesImages(service).subscribe(
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
