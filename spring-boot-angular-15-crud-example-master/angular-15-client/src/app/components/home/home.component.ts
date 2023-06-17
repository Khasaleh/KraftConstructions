import { Component, OnInit } from '@angular/core';
import { HomeServiceService } from '../../service/home-service.service'
import { data } from 'jquery';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css',
    '../../../styles.css'
  ]
})
export class HomeComponent implements OnInit {
  slides: string[] = [];
  currentSlide = 0;
  showSlide() {
    this.currentSlide = (this.currentSlide + 1) % this.slides.length;
  }

  apiData: any;
  sliderData: any;
  bannerData: any;
  bannerDescription: any;
  objects: any[] = [];
  activeSlideIndex: number = 0;
  globalUrl = 'http://99.72.32.144:8083'
  constructor(private homeService: HomeServiceService) { }
  ngOnInit(): void {
    setInterval(() => this.showSlide(), 5000);
    this.homeService.getHomePageBanner().subscribe(
      response => {
        this.slides = response.map((p: { id: any, imageUrl: any; }) => this.globalUrl + p.imageUrl); // Assuming the API response is an array of strings
        console.log(this.slides, "sides data"); // Check the updated slides array
      },
    )
    this.homeService.getHomePageData().subscribe(
      data => {
        this.apiData = data;
        console.log("ResponseData1", data);
      },
    )
    this.homeService.getsliderdata().subscribe(
      data => {
        this.sliderData = data;
        console.log("ResponseData2", data);
      },
    );
    this.homeService.getTestimonialsData().subscribe(
      response => {
        this.objects = response;
        console.log(this.objects, "response for test get data");
      },
      error => {
        console.log(error, "error for test data");
      }
    )
    this.homeService.getHomeBannerDescription().subscribe(
      response => {
        this.bannerDescription = response
        console.log(this.bannerDescription, "response for banner data");
      },
      error => {
        console.log(error, "error for banner data");
      }
    )

  }
  setActiveSlide(index: number) {
    this.activeSlideIndex = index;
  }
}
