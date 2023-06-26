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
this.getAll();
}
getAll () {
this.dashboardService.getAll().subscribe((res)=> {
  console.log(res);
  this.data=res;
})
}
// careerApplicationCount
// : 
// 2
// requestEstimateCount
// : 
// 1
// testimonialCount
// : 
// 1
}
