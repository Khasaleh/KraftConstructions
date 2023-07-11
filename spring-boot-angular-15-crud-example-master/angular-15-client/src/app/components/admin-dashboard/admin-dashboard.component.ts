import { Component } from '@angular/core';
import { dashboardService } from 'src/app/service/dashboard';
@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {
constructor(private dashboardService : dashboardService) {}
data!:any;
ngOnInit():void {
  this.dashboardService.getAll().subscribe((res)=> {
    this.data=res;
  })
  }
}

