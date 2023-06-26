import { Component } from '@angular/core';
import { error } from 'jquery';
import { InteriorRemodelingService } from 'src/app/service/interior-remodeling.service';

@Component({
  selector: 'app-intmod',
  templateUrl: './intmod.component.html',
  styleUrls: ['./intmod.component.css']
})
export class IntmodComponent {
  data!: any[];
  // serviceNames: string[] = [];

  constructor(private interiorRemodService: InteriorRemodelingService) { }
  ngOnInit() {
    this.interiorRemodService.getServiceByPage1().subscribe(
      response => {
        // this.serviceNames = response.map((service: { serviceName: any; }) => service.serviceName);
        // console.log(this.serviceNames);
        this.data = response
        console.log(this.data);
      },
      error => {
        console.log(error);

      }
    )
  }
  getOneService(service: any){
    this.interiorRemodService.getServiceById(service).subscribe(
      response =>{
        console.log(response);
        
      },
      error => {
        console.log(error);
        
      }
    )
  }
}
