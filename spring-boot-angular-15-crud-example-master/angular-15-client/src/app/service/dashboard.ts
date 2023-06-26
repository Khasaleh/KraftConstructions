import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
;

@Injectable({
  providedIn: 'root'
})
export class dashboardService {

  getAllUrl:string;

  constructor(private http : HttpClient) { 
  
  this.getAllUrl="http://99.72.32.144:8081/api/dashboard";


}

getAll():Observable<any> {
  return this.http.get<any>(this.getAllUrl);
  }
  
}