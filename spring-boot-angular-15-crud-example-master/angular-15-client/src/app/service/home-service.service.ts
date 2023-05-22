import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HomeServiceService {

  constructor(private http: HttpClient) { }
  getHomepageData(): Observable<any> {
    return this.http.get('http://99.72.32.144:8081/api/homepageabout-us/homepageupdate-description');
  }
}
