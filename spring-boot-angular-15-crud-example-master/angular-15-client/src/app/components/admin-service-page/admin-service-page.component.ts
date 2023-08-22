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
  errorMessage: string | null = null;
  successMessage: string | null = null;
constructor(private pagesService: InteriorRemodelingService){}

ngOnInit(): void {
  this.pagesService.getPagesdata().subscribe((response: any[]) => {
    this.tableData = response
  },
    error => {
      this.errorMessage = error.message;
          setTimeout(() => {
            this.errorMessage = '';
          }, 1000);
    }
  )
}
}
