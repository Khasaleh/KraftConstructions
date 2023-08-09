import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InteriorRemodelingService {
  
  constructor(private http: HttpClient) { }
  private dataSubject = new BehaviorSubject<any>(null);
  data$ = this.dataSubject.asObservable();
  updateData(data: any) {
    this.dataSubject.next(data);
  }
  //API for Pages section in services
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
  saveServicesImages(serviceImage: FormData, serviceId: number): Observable<any> {
    return this.http.post(`http://99.72.32.144:8081/api/service/${serviceId}/images`, serviceImage)
  }
  getServicesImages(serviceId: number): Observable<any>{
    return this.http.get(`http://99.72.32.144:8081/api/${serviceId}/images`)
  }

}