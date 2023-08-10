import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddCareerNewsService {

  apiUrl= 'https://api.kraftconstructionco.com/api';
  constructor(private http: HttpClient) { }
  getsliderdata(): Observable<any> {
    return this.http.get(this.apiUrl+'/news/')
  }
  careerNewsData(description: string ,backgroundColor: string, textColor: string, startDate: string, endDate: string, status: boolean): Observable<any>{
  return this.http.post(this.apiUrl+'/news',{
    "description":`${description}`,
    "backgroundColor":`${backgroundColor}`,
    "textColor":`${textColor}`,
    "startDate":`${startDate}`,
    "endDate":`${endDate}`,
    "status":`${status}`,
  })
  }
  toggleButton(): Observable<any>{
    return this.http.put(this.apiUrl+'/news/update-status',{})
  }
}
