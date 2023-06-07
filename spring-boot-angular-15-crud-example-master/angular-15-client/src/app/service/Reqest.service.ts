import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReqUser } from '../model/requsr';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReqUserService {
  ReqUserUrl: string;

  constructor(private http : HttpClient) { 
  this.ReqUserUrl = 'http://99.72.32.144:8081/api/estimate-request/saverequest';

}
ReqUser(usr: ReqUser):Observable<ReqUser[]> {
  return this.http.post<ReqUser[]>(this.ReqUserUrl,usr);
}
}