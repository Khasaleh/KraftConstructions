import { Component } from '@angular/core';
import { InteriorRemodelingService } from 'src/app/service/interior-remodeling.service';
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-admin-service-page',
  templateUrl: './admin-service-page.component.html',
  styleUrls: ['./admin-service-page.component.css']
})
export class AdminServicePageComponent {
  tableData!: any[];
constructor(private pagesService: InteriorRemodelingService){}

ngOnInit(): void {
  this.pagesService.getPagesdata().subscribe((response: any[]) => {
    this.tableData = response
  },
    error => {
      console.log(error);
    }
  )
}
}
