import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InteriorRemodelingService {

  constructor(private http: HttpClient) { }
  getServiceData(): Observable<any> {
    return this.http.get('http://99.72.32.144:8081/api/services');
  } 
  saveInteriorRemodelingpageData(serviceName : string, pageName : string, isActive : boolean): Observable<any> {
    return this.http.post('http://99.72.32.144:8081/api/addservices',{
      "serviceName":`${serviceName}`,
      "pageName":`${pageName}`,
      "isActive": `${isActive}`

  });
  }
  enableService(serviceId: number): Observable<any> {
    return this.http.patch(`http://99.72.32.144:8081/api/services/${serviceId}/enable`, {});
  }

  disableService(serviceId: number): Observable<any> {
    return this.http.patch(`http://99.72.32.144:8081/api/services/${serviceId}/disable`, {});
  }
}
