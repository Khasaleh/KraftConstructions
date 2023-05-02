import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ContactUsComponent } from '../components/contact-us/contact-us.component';
@Injectable({
  providedIn: 'root'
})
export class FormService {
  firstname!: string;
  lastname!: string;
  email!: string;
  phone!: number;
  message!: string;

  constructor(private http: HttpClient) { }

  submitForm() {
    const formData = {
      firstname: this.firstname,
      lastname: this.lastname,
      email: this.email,
      phone: this.phone,
      message: this.message
    };
    // const url = 'https://example.com/api/form-submit';
    // return this.http.post(url, formData);
    return formData;
  }
}
