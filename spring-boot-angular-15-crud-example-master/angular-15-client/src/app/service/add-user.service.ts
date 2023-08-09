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
  apiUrl= 'https://api.kraftconstructionco.com/api';
  constructor(private http : HttpClient) { 
  this.addUserURL = this.apiUrl+'/auth/users/create';
  this.getUserURL=  this.apiUrl+'/auth/users';
  this.updateURL=   this.apiUrl+'/auth/users/update';
  this.deleteURL=   this.apiUrl+'/auth/users/delete';
  this.uploadUrl =  this.apiUrl+'/auth/users/uploadprofile/'
}
AddUser(usr: AddUser):Observable<any> {
  return this.http.post<AddUser>(this.addUserURL,usr);
}
getAllUsers():Observable<AddUser[]> {
return this.http.get<AddUser[]>(this.getUserURL);
}
updateUser(user: AddUser, oldUsername: string): Observable<any> {
  return this.http.post<any>(`${this.updateURL}/${oldUsername}`, user);
}
deleteUser(username: string): Observable<any> {
  return this.http.delete<any>(`${this.deleteURL}/${username}`);
}
saveImage(profileImage:FormData, username:string) : Observable<any> {
  return this.http.post<any>(`${this.uploadUrl}${username}`,profileImage);
}
}
