import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CareerData } from '../model/career';

@Injectable({
  providedIn: 'root'
})
export class CareerDataService {
  CareerUrl: string;
  getAllUrl:string;
  deleteUrl:string;
  getUserById:string;
  constructor(private http : HttpClient) { 
  this.CareerUrl = 'http://99.72.32.144:8081/api/careers-applications';
  this.getAllUrl="http://99.72.32.144:8081/api/careers-applications";
  this.deleteUrl="http://99.72.32.144:8081/api/careers-applications";
  this.getUserById="http://99.72.32.144:8081/api/careers-applications";

}
SaveUser(user: FormData): Observable<CareerData> {
    return this.http.post<CareerData>(this.CareerUrl, user);
  }
  getAll():Observable<CareerData[]> {
    return this.http.get<CareerData[]>(this.getAllUrl);
    }
    deleteUser(id: number) {
      return this.http.delete<CareerData>(`${this.deleteUrl}/${id}`)
    }
    getUserbyId(id: number) :Observable<CareerData>{
      return this.http.get<CareerData>(`${this.getUserById}/${id}`)
    }
  }
