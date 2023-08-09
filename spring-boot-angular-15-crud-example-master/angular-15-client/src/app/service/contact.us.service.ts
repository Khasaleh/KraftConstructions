import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ContactUs } from '../model/conus';
import { Observable } from 'rxjs';
import { Address } from '../model/address';


@Injectable({
  providedIn: 'root'
})
export class ContactUsService {
  contactUsUrl: string;
  getAllUrl:string;
  deleteUrl:string;
  getContact:string;
  updateContactUrl:string;
  apiUrl= 'https://api.kraftconstructionco.com/api';
  constructor(private http : HttpClient) { 
  this.contactUsUrl=this.apiUrl+"/contact-us";
  this.getAllUrl=this.apiUrl+"/contact-us";
  this.deleteUrl=this.apiUrl+"/contact-us";
  this.getContact=this.apiUrl+"/contact-address";
  this.updateContactUrl =this.apiUrl+"/contact-address/update-address";

}
saveData(user:ContactUs): Observable<ContactUs> {
    return this.http.post<ContactUs>(this.contactUsUrl,user);
  }
  getAll():Observable<ContactUs[]> {
    return this.http.get<ContactUs[]>(this.getAllUrl);
    }
    deleteUser(id: number) :Observable<any>{
      return this.http.delete<ContactUs>(`${this.deleteUrl}/${id}`)
    }  
    getContactAll() {
      return this.http.get<any>(this.getContact);
    }
    updateContact (data:Address) {
      return this.http.post<any>(this.updateContactUrl,data);
    }
  }
