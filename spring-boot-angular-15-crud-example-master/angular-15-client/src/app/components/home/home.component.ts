import { Component,OnInit } from '@angular/core';
import {HomeServiceService} from '../../service/home-service.service'
import { data } from 'jquery';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
   styleUrls: ['./home.component.css',
   '../../../styles.css'
]
})
export class HomeComponent implements OnInit {
  slides: string[] = ['../../../assets/banner.png', '../../../assets/testimonial.png', '../../../assets/career-banner.png'];
  currentSlide = 0;

  

  showSlide() {
    this.currentSlide = (this.currentSlide + 1) % this.slides.length;
  }
  apiData: any;
  sliderData: any;
  globalUrl = 'http://99.72.32.144:8083'
  constructor(private homeService:HomeServiceService){}
  ngOnInit(): void {
    setInterval(() => this.showSlide(), 5000);
    this.homeService.getHomePageData().subscribe(
    data => {
      this.apiData = data;
      console.log("ResponseData",data);
    },
    )
    this.homeService.getsliderdata().subscribe(
      data => {
        this.sliderData = data;
        console.log("ResponseData",data);
      },
      )
  }
}
