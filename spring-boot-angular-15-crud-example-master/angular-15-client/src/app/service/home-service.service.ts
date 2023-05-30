import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HomeServiceService {

  constructor(private http: HttpClient) { }
  saveVideo(video: FormData): Observable<any> {
    return this.http.post('http://99.72.32.144:8081/api/homepageabout-us/upload-video',{
      "video":`${video}`,
  });
  }
  saveHomepageData(aboutUsDescription : string, aboutUsLink : string): Observable<any> {
    return this.http.post('http://99.72.32.144:8081/api/homepageabout-us/homepageupdate-description',{
      "aboutusLink":`${aboutUsLink}`,
      "aboutusDescription":`${aboutUsDescription}`
  });
  }
}