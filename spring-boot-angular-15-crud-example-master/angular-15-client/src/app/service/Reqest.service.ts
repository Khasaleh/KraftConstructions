import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReqUser } from '../model/requsr';
import { Observable } from 'rxjs';
import { AddUser } from '../model/add-user';

@Injectable({
  providedIn: 'root'
})
export class ReqUserService {
  ReqUserUrl: string;
  getAllUrl:string;
  deleteUrl:string;
  getUserById:string;
  constructor(private http : HttpClient) { 
  this.ReqUserUrl = 'http://99.72.32.144:8081/api/estimate-request/saverequest';
  this.getAllUrl="http://99.72.32.144:8081/api/estimate-request/getrequests";
  this.deleteUrl="http://99.72.32.144:8081/api/estimate-request/delete";
  this.getUserById="http://99.72.32.144:8081/api/estimate-request/getrequests";

}
ReqUser(user: ReqUser): Observable<AddUser> {
  return this.http.post<AddUser>(this.ReqUserUrl, user);
}
getAll():Observable<ReqUser[]> {
  return this.http.get<ReqUser[]>(this.getAllUrl);
  }
  deleteUser(id: number) {
    return this.http.delete<ReqUser>(`${this.deleteUrl}/${id}`)
  }
  getUserbyId(id: number) :Observable<ReqUser>{
    return this.http.get<ReqUser>(`${this.getUserById}/${id}`)
  }
}