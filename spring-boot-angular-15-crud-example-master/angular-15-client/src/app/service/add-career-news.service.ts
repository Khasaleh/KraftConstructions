import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddCareerNewsService {
  // AddCareerNewsService(textEditor: any, addLink: any) {
  //   throw new Error('Method not implemented.');
  // }

  constructor(private http: HttpClient) { }
  getsliderdata(): Observable<any> {
    return this.http.get('http://99.72.32.144:8081/api/news/')
  }
  careerNewsData(description: string ,backgroundColor: string, textColor: string, startDate: string, endDate: string, status: boolean): Observable<any>{
  return this.http.post('http://99.72.32.144:8081/api/news',{
    "description":`${description}`,
    "backgroundColor":`${backgroundColor}`,
    "textColor":`${textColor}`,
    "startDate":`${startDate}`,
    "endDate":`${endDate}`,
    "status":`${status}`,
  })
  }
  toggleButton(): Observable<any>{
    return this.http.put('http://99.72.32.144:8081/api/news/update-status',{})
  }
}
