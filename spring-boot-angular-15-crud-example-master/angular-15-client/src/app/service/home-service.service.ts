import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, retry } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HomeServiceService {
  apiUrl= 'https://api.kraftconstructionco.com/api';
  constructor(private http: HttpClient) { }
  getServicesData(): Observable <any> {
    return this.http.get(this.apiUrl+'/homepageabout-us/1/services')
  }
  getServiceByPage1(): Observable<any> {
    return this.http.get(this.apiUrl+'/pageServices/Interior Remodelling')
  }
  getServiceByPage2(): Observable<any> {
    return this.http.get(this.apiUrl+'/pageServices/New Addition')
  }
  getHomeBannerDescription(): Observable<any> {
    return this.http.get(this.apiUrl+'/homepageabout-us/banner')
  }
  getTestimonialsData(): Observable<any>{
    return this.http.get(this.apiUrl+'/testimonial-homepage')
  }
  getsliderdata(): Observable<any> {
    return this.http.get(this.apiUrl+'/news/')
  }
  getHomePageBanner(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl + '/homepage-banner');
  }

  getHomePageData(): Observable<any> {
    return this.http.get(this.apiUrl+'/homepageabout-us');
  }
  saveVideo(video: FormData): Observable<any> {
    return this.http.post(this.apiUrl+'/homepageabout-us/upload-video', video);
  }
  saveHomepageData(aboutUsDescription: string, aboutUsLink: string): Observable<any> {
    return this.http.post(this.apiUrl+'/homepageabout-us/homepageupdate-description', {
      "aboutusLink": `${aboutUsLink}`,
      "aboutusDescription": `${aboutUsDescription}`
    });
  }
  saveBannnerImage(images: FormData): Observable<any>{
    return this.http.post(this.apiUrl+'/homepage-banner',images)
  }
  saveBannerData(bannerLink: string,bannerDescription:string, linkStatus:boolean): Observable<any>{
    return this.http.post(this.apiUrl+'/homepageabout-us/update-banner',{
      "bannerLink": `${bannerLink}`,
      "bannerDescription": `${bannerDescription}`,
      "linkStatus": linkStatus
    })
  }
  saveTestimonialData(testimonials: any[]): Observable<any> {
  const payload = testimonials.map(testimonial => ({
    heading: testimonial.heading,
    description: testimonial.description,
    name: testimonial.name
  }));

  return this.http.post(this.apiUrl+'/testimonial-homepage', payload);
}
bannerLinkStatus(): Observable<any>{
  return this.http.put(this.apiUrl+'/homepageabout-us/banner-link-status',{})
}

addServicesData(serviceId: any[]): Observable<any> {
  const url = this.apiUrl+'/homepageabout-us/1/addservices';
  const requestBody = { serviceIds:  serviceId};

  return this.http.post(url, requestBody);
}
deleteAllTestimonial(): Observable<any>{
 return this.http.delete(this.apiUrl+'/testimonial-homepage/delete-all')
}
deleteBannerImage(bannerId: number): Observable<any> {
  return this.http.delete(this.apiUrl+`/homepage-banner/${bannerId}`)
}

}
