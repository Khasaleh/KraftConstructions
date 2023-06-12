import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HomeServiceService {

  constructor(private http: HttpClient) { }
  getsliderdata(): Observable<any> {
    return this.http.get('http://99.72.32.144:8081/api/news/')
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


}
