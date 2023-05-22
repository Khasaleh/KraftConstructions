import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  apiUrl= "http://99.72.32.144:8081/api/auth/signin";

  constructor(private http:HttpClient) {
  }
  proceedlogin(usercred:any) {
    return this.http.post(this.apiUrl,usercred)

  }
  Isloggedin() {
    return localStorage.getItem('token')!=null;
  }
}
