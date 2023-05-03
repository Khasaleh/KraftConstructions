import { Injectable } from '@angular/core';
import { HttpClient,HttpClientModule } from '@angular/common/http';
import { AddUser } from '../model/add-user';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AddUserService {
  addUserURL: string;
  getUserURL : string;
  updateURL : string;
  constructor(private http : HttpClient) { 
  this.addUserURL = 'http://localhost:9091/user/addUser';
  this.getUserURL= 'http://localhost:9091/user/getAll';
  this.updateURL='http://localhost:9091/user/updateEmployee';
}
AddUser(usr: AddUser): Observable<AddUser> {
  return this.http.post<AddUser>(this.addUserURL,usr);
}
getAllUsers():Observable<AddUser[]> {
return this.http.get<AddUser[]>(this.getUserURL);
}
UpdateUser(usr: AddUser) : Observable<AddUser> {
  return this.http.put<AddUser>(this.updateURL, usr);
} 
}
