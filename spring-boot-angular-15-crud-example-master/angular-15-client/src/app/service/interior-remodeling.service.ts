import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InteriorRemodelingService {
  constructor(private http: HttpClient) { }
  
  //API for Pages section in services
  getServicesbyPage(): Observable<any>{
    return this.http.get('http://99.72.32.144:8081/api/services/22/details')
  }
  getServicebyPage(): Observable<any>{
    return this.http.get('http://99.72.32.144:8081/api/services/23/details')
  }
  getPagesdata(): Observable<any> {
    return this.http.get('http://99.72.32.144:8081/api/services/details')
  }
  getServiceById(serviceId: number): Observable<any> {
    return this.http.get(`http://99.72.32.144:8081/api/services/${serviceId}/details`)
  }
  getServiceByPage1(): Observable<any> {
    return this.http.get('http://99.72.32.144:8081/api/pageServices/Interior Remodelling')
  }
  getServiceByPage2(): Observable<any> {
    return this.http.get('http://99.72.32.144:8081/api/pageServices/New Addition')
  }
  getServiceData(): Observable<any> {
    return this.http.get('http://99.72.32.144:8081/api/services');
  }
  saveInteriorRemodelingpageData(serviceName: string, pageName: string, isActive: boolean): Observable<any> {
    return this.http.post('http://99.72.32.144:8081/api/addservices', {
      "serviceName": `${serviceName}`,
      "pageName": `${pageName}`,
      "isActive": `${isActive}`
    });
  }
  enableService(serviceId: number): Observable<any> {
    return this.http.patch(`http://99.72.32.144:8081/api/${serviceId}/enable`, {});
  }

  disableService(serviceId: number): Observable<any> {
    return this.http.patch(`http://99.72.32.144:8081/api/${serviceId}/disable`, {});
    // http://99.72.32.144:8081/api/8/enable
  };
  saveSeviceDetails(serviceData: FormData): Observable<any> {
    return this.http.post('http://99.72.32.144:8081/api/services/addDetails', serviceData);
  }
  saveServicesImages(serviceImage: FormData): Observable<any> {
    return this.http.post('http://99.72.32.144:8081/api/service/1/images', serviceImage)
  }


}