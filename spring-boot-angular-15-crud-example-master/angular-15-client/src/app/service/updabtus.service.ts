import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { aboutusdata } from '../data-type';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class UpdabtusService {
 apiUrl= 'http://99.72.32.144:8081/api';
  constructor( private http: HttpClient ) { }
  addata(data:aboutusdata) {
    console.log("service called");
    return this.http.post( this.apiUrl+'/about-us/update-description', data);
   
  }
  showdata() {
    return this.http.get(this.apiUrl+'/about-us');
  }
  saveImage(image: FormData): Observable<any> {
    return this.http.post('http://99.72.32.144:8081/api/about-us/upload-image', image);
  }
  
}
