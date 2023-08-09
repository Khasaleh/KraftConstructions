import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Testimonial } from '../model/testimonial.model';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class TestimonialService {
  SaveUrl: string;
  apiUrl= 'https://api.kraftconstructionco.com/api';
  getAllUrl:string;
  deleteUrl:string;
  getUserById:string;
  getapprovedUrl:string;
  approveUrl:string;
  hideUrl:string;
  saveImageUrl:string;
  getImageUrl:string;
  deleteImageUrl:string;
  sliderUrl:string
  getSliderUrl:string
  deleteSliderUrl:string;
  getSliderbyIdUrl:string
  constructor(private http : HttpClient) { 
  this.SaveUrl = this.apiUrl+'/reviews';
  this.getAllUrl=this.apiUrl+"/reviews";
  this.deleteUrl=this.apiUrl+"/reviews";
  this.getUserById=this.apiUrl+"/reviews";
  this.getapprovedUrl=this.apiUrl+"/reviews/approved";
  this.approveUrl=this.apiUrl+"/reviews/approve";
  this.hideUrl=this.apiUrl+"/reviews/hide";
  this.saveImageUrl=this.apiUrl+"/testimonials/images";
  this.getImageUrl=this.apiUrl+"/testimonials/TESTIMONIAL";
  this.deleteImageUrl=this.apiUrl+"/testimonials";
  this.sliderUrl=this.apiUrl+"/testimonial-slider";
  this.getSliderUrl=this.apiUrl+"/testimonial-slider";
  this.deleteSliderUrl=this.apiUrl+"/testimonial-slider";
 this.getSliderbyIdUrl= this.apiUrl+"/testimonial-slider";
}
PostTest(user: Testimonial): Observable<Testimonial> {
  return this.http.post<Testimonial>(this.SaveUrl, user);
}
getAll():Observable<Testimonial[]> {
  return this.http.get<Testimonial[]>(this.getAllUrl);
  }
  deleteTest(id: number) : Observable<any> {
    return this.http.delete<Testimonial>(`${this.deleteUrl}/${id}`)
  }
  getTestbyId(id: number) :Observable<Testimonial>{
    return this.http.get<Testimonial>(`${this.getUserById}/${id}`)
  }
  getapproveTest():Observable<Testimonial[]> {
    return this.http.get<Testimonial[]>(this.getapprovedUrl);
  }
  approveTest(user: Testimonial,id: number):Observable<any> {
    return this.http.put<Testimonial>(`${this.approveUrl}/${id}`,user);
  }
  hideTest(user:Testimonial,id:number) : Observable<any> {
    return this.http.put<Testimonial>(`${this.hideUrl}/${id}`,user)
  }
  saveImage(data: FormData): Observable<any> {
    return this.http.post(this.saveImageUrl, data);
  }
  getImage() : Observable<any> {
    return this.http.get<FormData>(this.getImageUrl);
}
deleteImage(id: number) : Observable<any> {
  return this.http.delete(`${this.deleteImageUrl}/${id}`)
}
saveSlider(data :FormData) : Observable<any> {
  return this.http.post(this.sliderUrl, data);
}
getSlider() : Observable<any>  {
  return this.http.get(this.getSliderUrl);
}
deleteSlider(id:number): Observable<any> {
return this.http.delete(`${this.deleteSliderUrl}/${id}`)
}

}