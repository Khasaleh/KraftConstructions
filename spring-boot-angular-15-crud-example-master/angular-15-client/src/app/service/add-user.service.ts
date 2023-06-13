import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AddUser } from '../model/add-user';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AddUserService {
  addUserURL: string;
  getUserURL : string;
  updateURL : string;
  deleteURL: string;
  uploadUrl:string;
  constructor(private http : HttpClient) { 
  this.addUserURL = 'http://99.72.32.144:8081/api/auth/users/create';
  this.getUserURL=  'http://99.72.32.144:8081/api/auth/users';
  this.updateURL=   'http://99.72.32.144:8081/api/auth/users/update';
  this.deleteURL=   'http://99.72.32.144:8081/api/auth/users/delete';
  this.uploadUrl = 'http://99.72.32.144:8081/api/auth/users/uploadprofile/'
}
AddUser(usr: AddUser):Observable<AddUser> {
  return this.http.post<AddUser>(this.addUserURL,usr);
}
getAllUsers():Observable<AddUser[]> {
return this.http.get<AddUser[]>(this.getUserURL);
}
updateUser(user: AddUser, oldUsername: string): Observable<any> {
  return this.http.put<any>(`${this.updateURL}/${oldUsername}`, user);
}
deleteUser(username: string): Observable<any> {
  return this.http.delete<any>(`${this.deleteURL}/${username}`);
}
saveImage(profileImage:FormData, username:string) : Observable<any> {
  return this.http.post<any>(`${this.uploadUrl}${username}`,profileImage);
}
}
