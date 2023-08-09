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
  apiUrl= 'https://api.kraftconstructionco.com/api';
  constructor(private http : HttpClient) { 
  this.ReqUserUrl= this.apiUrl+'/estimate-request/saverequest';
  this.getAllUrl=  this.apiUrl+"/estimate-request/getrequests";
  this.deleteUrl=  this.apiUrl+"/estimate-request/delete";
  this.getUserById=this.apiUrl+"/estimate-request/getrequests";

}
ReqUser(user: ReqUser): Observable<any> {
  return this.http.post<AddUser>(this.ReqUserUrl, user);
}
getAll():Observable<ReqUser[]> {
  return this.http.get<ReqUser[]>(this.getAllUrl);
  }
  deleteUser(id: number) : Observable<any>{
    return this.http.delete<ReqUser>(`${this.deleteUrl}/${id}`)
  }
  getUserbyId(id: number) :Observable<ReqUser>{
    return this.http.get<ReqUser>(`${this.getUserById}/${id}`)
  }
}