import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InteriorRemodelingService {
  apiUrl= 'https://api.kraftconstructionco.com/api';
  constructor(private http: HttpClient) { }
  private dataSubject = new BehaviorSubject<any>(null);
  data$ = this.dataSubject.asObservable();
  updateData(data: any) {
    this.dataSubject.next(data);
  }
  //API for Pages section in services
  getServicebyPage(): Observable<any>{
    return this.http.get(this.apiUrl+'/services/23/details')
  }
  getPagesdata(): Observable<any> {
    return this.http.get(this.apiUrl+'/services/details')
  }
  getServiceById(serviceId: number): Observable<any> {
    return this.http.get(this.apiUrl+`/services/${serviceId}/details`)
  }
  getServiceByPage1(): Observable<any> {
    return this.http.get(this.apiUrl+'/pageServices/Interior Remodelling')
  }
  getServiceByPage2(): Observable<any> {
    return this.http.get(this.apiUrl+'/pageServices/New Addition')
  }
  getServiceData(): Observable<any> {
    return this.http.get(this.apiUrl+'/services');
  }
  saveInteriorRemodelingpageData(serviceName: string, pageName: string, isActive: boolean): Observable<any> {
    return this.http.post(this.apiUrl+'/addservices', {
      "serviceName": `${serviceName}`,
      "pageName": `${pageName}`,
      "isActive": `${isActive}`
    });
  }
  enableService(serviceId: number): Observable<any> {
    return this.http.patch(this.apiUrl+`/${serviceId}/enable`, {});
  }

  disableService(serviceId: number): Observable<any> {
    return this.http.patch(this.apiUrl+`/${serviceId}/disable`, {});
  };
  saveSeviceDetails(serviceData: FormData): Observable<any> {
    return this.http.post(this.apiUrl+'/services/addDetails', serviceData);
  }
  saveServicesImages(serviceImage: FormData, serviceId: number): Observable<any> {
    return this.http.post(this.apiUrl+`/service/${serviceId}/images`, serviceImage)
  }
  getServicesImages(serviceId: number): Observable<any>{
    return this.http.get(this.apiUrl+`/${serviceId}/images`)
  }
  deleteService(serviceId: number): Observable<any>{
    return this.http.delete(this.apiUrl+`/delete-service/${serviceId}`)
  }

}