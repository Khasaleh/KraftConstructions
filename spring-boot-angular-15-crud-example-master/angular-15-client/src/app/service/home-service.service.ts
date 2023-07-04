import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, retry } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HomeServiceService {

  constructor(private http: HttpClient) { }
  getServiceByPage1(): Observable<any> {
    return this.http.get('http://99.72.32.144:8081/api/pageServices/Interior Remodelling')
  }
  getServiceByPage2(): Observable<any> {
    return this.http.get('http://99.72.32.144:8081/api/pageServices/New Addition')
  }
  getHomeBannerDescription(): Observable<any> {
    return this.http.get('http://99.72.32.144:8081/api/homepageabout-us/banner')
  }
  getTestimonialsData(): Observable<any>{
    return this.http.get('http://99.72.32.144:8081/api/testimonial-homepage')
  }
  getsliderdata(): Observable<any> {
    return this.http.get('http://99.72.32.144:8081/api/news/')
  }
  getHomePageBanner(): Observable<any> {
    return this.http.get('http://99.72.32.144:8081/api/homepage-banner');
  }
  getHomePageData(): Observable<any> {
    return this.http.get('http://99.72.32.144:8081/api/homepageabout-us');
  }
  saveVideo(video: FormData): Observable<any> {
    return this.http.post('http://99.72.32.144:8081/api/homepageabout-us/upload-video', video);
  }
  saveHomepageData(aboutUsDescription: string, aboutUsLink: string): Observable<any> {
    return this.http.post('http://99.72.32.144:8081/api/homepageabout-us/homepageupdate-description', {
      "aboutusLink": `${aboutUsLink}`,
      "aboutusDescription": `${aboutUsDescription}`
    });
  }
  saveBannnerImage(images: FormData): Observable<any>{
    return this.http.post('http://99.72.32.144:8081/api/homepage-banner',images)
  }
  saveBannerData(bannerLink: string,bannerDescription:string, linkStatus:boolean): Observable<any>{
    return this.http.post('http://99.72.32.144:8081/api/homepageabout-us/update-banner',{
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

  return this.http.post('http://99.72.32.144:8081/api/testimonial-homepage', payload);
}
bannerLinkStatus(): Observable<any>{
  return this.http.put('http://99.72.32.144:8081/api/homepageabout-us/banner-link-status',{})
}
// addServicesData(serviceIds: number[]): Observable<any>{
//   const url = 'http://99.72.32.144:8081/api/homepageabout-us/1/addservices';
  
//   const requestBody = { serviceIds };
//     return this.http.post(url, requestBody)

// }
addServicesData(serviceId: any[]): Observable<any> {
  const url = 'http://99.72.32.144:8081/api/homepageabout-us/1/addservices';
  const requestBody = { serviceIds:  serviceId};

  // requestBody.serviceIds[] = serviceId;

  return this.http.post(url, requestBody);
}

}
