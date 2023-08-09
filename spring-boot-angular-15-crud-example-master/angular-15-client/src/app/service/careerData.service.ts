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
  apiUrl= 'https://api.kraftconstructionco.com/api';
  constructor(private http : HttpClient) { 
   
  this.CareerUrl = this.apiUrl+'/careers-applications';
  this.getAllUrl= this.apiUrl+"/careers-applications";
  this.deleteUrl= this.apiUrl+"/careers-applications";
  this.getUserById= this.apiUrl+"/careers-applications";

}
SaveUser(user: FormData): Observable<any> {
    return this.http.post<CareerData>(this.CareerUrl, user);
  }
  getAll():Observable<CareerData[]> {
    return this.http.get<CareerData[]>(this.getAllUrl);
    }
    deleteUser(id: number) : Observable<any>{
      return this.http.delete<CareerData>(`${this.deleteUrl}/${id}`)
    }
    getUserbyId(id: number) :Observable<CareerData>{
      return this.http.get<CareerData>(`${this.getUserById}/${id}`)
    }
  }
