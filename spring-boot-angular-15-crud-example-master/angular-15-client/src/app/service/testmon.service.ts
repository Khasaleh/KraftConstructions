import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Testimonial } from '../model/testimonial.model';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class TestimonialService {
  SaveUrl: string;
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
  this.SaveUrl = 'http://99.72.32.144:8081/api/reviews';
  this.getAllUrl="http://99.72.32.144:8081/api/reviews";
  this.deleteUrl="http://99.72.32.144:8081/api/reviews";
  this.getUserById="http://99.72.32.144:8081/api/reviews";
  this.getapprovedUrl="http://99.72.32.144:8081/api/reviews/approved";
  this.approveUrl="http://99.72.32.144:8081/api/reviews/approve";
  this.hideUrl="http://99.72.32.144:8081/api/reviews/hide";
  this.saveImageUrl="http://99.72.32.144:8081/api/testimonials/images";
  this.getImageUrl="http://99.72.32.144:8081/api/testimonials/TESTIMONIAL";
  this.deleteImageUrl="http://99.72.32.144:8081/api/testimonials";
  this.sliderUrl="http://99.72.32.144:8081/api/testimonial-slider";
  this.getSliderUrl="http://99.72.32.144:8081/api/testimonial-slider";
  this.deleteSliderUrl="http://99.72.32.144:8081/api/testimonial-slider";
 this.getSliderbyIdUrl= "http://99.72.32.144:8081/api/testimonial-slider";
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